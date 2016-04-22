package com.hackaton.inloki.ws.request;

/**
 * Created by zruzicka on 22.04.2016.
 */
public class NeighbourBeaconRequest extends AbstractBeaconRequest implements ParametrizedRequest {

    private final String beaconId;

    public NeighbourBeaconRequest(String beaconId) {
        super("neighbours");
        this.beaconId = beaconId;
    }

    @Override
    public String getParameter() {
        return beaconId;
    }

}
