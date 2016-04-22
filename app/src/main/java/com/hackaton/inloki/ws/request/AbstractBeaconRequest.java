package com.hackaton.inloki.ws.request;

/**
 * Created by zruzicka on 22.04.2016.
 */
public abstract class AbstractBeaconRequest implements BeaconRequest {

    private final String requestPart;

    public AbstractBeaconRequest(String requestPart) {
        this.requestPart = requestPart;
    }

    @Override
    public String getRequestPart() {
        return requestPart;
    }

}
