package com.hackaton.inloki.ws;

import com.hackaton.inloki.ws.request.BeaconRequest;

/**
 * Created by zruzicka on 22.04.2016.
 */
public interface ServerDataProvider {

    public void sendBeaconRequest(BeaconRequest request);

}
