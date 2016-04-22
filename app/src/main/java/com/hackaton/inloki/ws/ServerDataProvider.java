package com.hackaton.inloki.ws;

import com.hackaton.inloki.ws.request.BeaconRequest;
import com.hackaton.inloki.ws.request.ParametrizedRequest;

/**
 * Created by zruzicka on 22.04.2016.
 */
public interface ServerDataProvider {

    public void sendRequest(BeaconRequest request);

    public void sendParametrizedRequest(ParametrizedRequest request);

}
