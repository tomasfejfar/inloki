package com.hackaton.inloki.ws.request;

/**
 * Created by zruzicka on 22.04.2016.
 */
public class PathElementsRequest extends AbstractBeaconRequest implements ParametrizedRequest {

    private final String pathId;

    public PathElementsRequest(String beaconId) {
        super("paths");
        this.pathId = beaconId;
    }

    @Override
    public String getParameter() {
        return pathId;
    }

}
