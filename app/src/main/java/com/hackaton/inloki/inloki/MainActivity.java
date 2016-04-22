package com.hackaton.inloki.inloki;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kontakt.sdk.android.ble.configuration.ScanPeriod;
import com.kontakt.sdk.android.ble.configuration.scan.IBeaconScanContext;
import com.kontakt.sdk.android.ble.configuration.scan.ScanContext;
import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.discovery.BluetoothDeviceEvent;
import com.kontakt.sdk.android.ble.discovery.EventType;
import com.kontakt.sdk.android.ble.discovery.ibeacon.IBeaconAdvertisingPacket;
import com.kontakt.sdk.android.ble.filter.ibeacon.IBeaconFilter;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.manager.ProximityManagerContract;
import com.kontakt.sdk.android.common.KontaktSDK;
import com.kontakt.sdk.android.common.profile.DeviceProfile;
import com.kontakt.sdk.android.common.profile.RemoteBluetoothDevice;
import com.kontakt.sdk.android.manager.KontaktProximityManager;

import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements ProximityManager.ProximityListener {

    private static final String TAG = "InLoki ---->";

    private ProximityManagerContract proximityManager;
    private ScanContext scanContext;
    private TextView textView;
    private long lastMessageTS;
    private int lastProximity = 100;
    private RelativeLayout contentLayout;
    private Vibrator vibrator;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KontaktSDK.initialize("api-key");
        this.lastMessageTS = new Date().getTime();
        proximityManager = new KontaktProximityManager(this);
        this.vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
    }

    protected void onStart() {
        super.onStart();
        this.textView = (TextView) findViewById(R.id.result_text);
        this.contentLayout = (RelativeLayout) findViewById(R.id.content_layout);
        proximityManager.initializeScan(getScanContext(), new OnServiceReadyListener() {
            public void onServiceReady() {
                proximityManager.attachListener(MainActivity.this);
            }

            public void onConnectionFailure() {

            }
        });
    }

    protected void onStop() {
        super.onStop();
        proximityManager.detachListener(this);
        proximityManager.disconnect();
    }

    private ScanContext getScanContext() {
        if (scanContext == null) {
            scanContext = new ScanContext.Builder()
                    .setScanPeriod(ScanPeriod.RANGING) // or for monitoring for 15 seconds scan and 10 seconds waiting:
//                    .setScanPeriod(new ScanPeriod(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS.toMillis(5)))
                    .setScanMode(ProximityManager.SCAN_MODE_BALANCED)
                    .setIBeaconScanContext(new IBeaconScanContext.Builder()
                        .setEventTypes(EnumSet.of(
                                EventType.DEVICE_DISCOVERED,
                                EventType.DEVICE_LOST,
                                EventType.DEVICES_UPDATE
                        ))
                        .setIBeaconFilters(Collections.singleton(new IBeaconFilter() {
                            public boolean apply(IBeaconAdvertisingPacket iBeaconAdvertisingPacket) {
                                final String uniqId = iBeaconAdvertisingPacket.getBeaconUniqueId();

                                return uniqId != null && uniqId.equals("5wKj"); // || uniqId != null && uniqId.equals("9aqg");

                            }
                        }))
                        .setDevicesUpdateCallbackInterval(TimeUnit.SECONDS.toMillis(1))
                        .build()
                    )
                    .build();
        }
        return scanContext;
    }

    public void onEvent(BluetoothDeviceEvent bluetoothDeviceEvent) {
        long now = new Date().getTime();
        long deltaTime = now - this.lastMessageTS;

        boolean shouldShowMessage = true;
        List<? extends RemoteBluetoothDevice> deviceList = bluetoothDeviceEvent.getDeviceList();
        long timestamp = bluetoothDeviceEvent.getTimestamp();
        DeviceProfile deviceProfile = bluetoothDeviceEvent.getDeviceProfile();
        String text = "Unknown event!";
        int proximity = this.lastProximity;

        switch (bluetoothDeviceEvent.getEventType()) {
            case SPACE_ENTERED:
                text = "namespace or region entered";
                break;
            case DEVICE_DISCOVERED:
                text = "found new beacon";
                break;
            case DEVICES_UPDATE:
                text = "updated beacons";
                break;
            case DEVICE_LOST:
                text = "lost device";
                break;
            case SPACE_ABANDONED:
                text = "namespace or region abandoned";
                break;
        }
        if (!deviceList.isEmpty()) {
            for (RemoteBluetoothDevice beacon : deviceList) {
                text += "| " + beacon.getName()
                        + "| " + beacon.getUniqueId()
                        + "| " + beacon.getDistance()
                        + "| " + beacon.getProximity().ordinal()
                ;
                proximity = this.getOwnProximity(beacon.getDistance());
                break;
            }
        }
        Log.d(TAG, text + " really?" + shouldShowMessage + " | " + proximity);
        this.lastProximity = Math.min(proximity, this.lastProximity);
//        if (shouldShowMessage && this.lastProximity != proximity) {
        if (deltaTime > 400) {
            long[] vibrationRythm = {(2 - proximity) * 50, 50, (2 - proximity) * 50, 50};
            this.vibrator.vibrate(vibrationRythm, -1);
            this.textView.setText(text);
            this.contentLayout.setBackgroundColor(this.getDistanceColor(proximity));
            this.lastMessageTS = now;
            this.lastProximity = 100;
        }
    }

    public void onScanStart() {
        Log.d(TAG, "scan started");
    }

    public void onScanStop() {
        Log.d(TAG, "scan stopped");
    }

    private int getOwnProximity(double dist) {
        if (dist < 1) {
            return 0;
        } else if (dist < 2) {
            return 1;
//        } else if (dist < 0.9) {
//            return 2;
//        } else if (dist < 1.5) {
//            return 3;
//        } else if (dist < 2) {
//            return 4;
        } else {
            return 100;
        }
    }

    private int getDistanceColor(int proximity) {
        int color;
        switch (proximity) {
            case 0:
                color = Color.rgb(0, 255, 0);
                break;
            case 1:
                color = Color.rgb(255, 255, 51);
                break;
            default:
                color = Color.rgb(255, 0, 0);
                break;
        }

        return color;
    }
}