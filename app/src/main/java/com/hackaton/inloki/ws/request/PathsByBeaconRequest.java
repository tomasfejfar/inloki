package com.hackaton.inloki.ws.request;

/**
 * Created by zruzicka on 22.04.2016.
 */
public class PathsByBeaconRequest extends AbstractBeaconRequest implements ParametrizedRequest {

    private final String beaconId;

    public PathsByBeaconRequest(String beaconId) {
        super("pathsbybeacon");
        this.beaconId = beaconId;
    }

    @Override
    public String getParameter() {
        return beaconId;
    }

}
