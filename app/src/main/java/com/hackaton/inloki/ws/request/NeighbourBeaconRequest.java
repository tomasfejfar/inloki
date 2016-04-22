package com.hackaton.inloki.ws.request;

/**
 * Created by zruzicka on 22.04.2016.
 */
public class NeighbourBeaconRequest extends AbstractBeaconRequest {

    private final String[] beaconIds;

    public NeighbourBeaconRequest(String[] beaconIds) {
        super("neighbour");
        this.beaconIds = beaconIds;
    }

}
