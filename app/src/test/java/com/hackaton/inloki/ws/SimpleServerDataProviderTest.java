package com.hackaton.inloki.ws;

import com.hackaton.inloki.ws.request.ApiTestBeaconRequest;
import com.hackaton.inloki.ws.request.BeaconRequest;
import com.hackaton.inloki.ws.request.NeighbourBeaconRequest;

import org.junit.Test;

/**
 * Created by zruzicka on 22.04.2016.
 */
public class SimpleServerDataProviderTest {

    private final ServerDataProvider mProvider = new SimpleServerDataProvider();

    @Test
    public void testNeighbourRequest() {
        String[] ids = new String[] {"id1", "id2"};
        BeaconRequest request = new NeighbourBeaconRequest(ids);
        mProvider.sendBeaconRequest(request);
    }

    @Test
    public void testApiTestRequest() {
        BeaconRequest request = new ApiTestBeaconRequest();
        mProvider.sendBeaconRequest(request);
    }
}
