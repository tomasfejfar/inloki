package com.hackaton.inloki.ws;

import com.hackaton.inloki.ws.request.ApiTestBeaconRequest;
import com.hackaton.inloki.ws.request.BeaconRequest;
import com.hackaton.inloki.ws.request.NeighbourBeaconRequest;
import com.hackaton.inloki.ws.request.ParametrizedRequest;
import com.hackaton.inloki.ws.request.PathElementsRequest;
import com.hackaton.inloki.ws.request.PathsByBeaconRequest;

import org.junit.Test;

/**
 * Created by zruzicka on 22.04.2016.
 */
public class SimpleServerDataProviderTest {

    private final ServerDataProvider mProvider = new SimpleServerDataProvider();

    @Test
    public void testPathsByBeaconRequest() {
        ParametrizedRequest request = new PathsByBeaconRequest("id1");
        mProvider.sendParametrizedRequest(request);
    }

    @Test
    public void testPathElementsRequest() {
        ParametrizedRequest request = new PathElementsRequest("id1");
        mProvider.sendParametrizedRequest(request);
    }

    @Test
    public void testApiTestRequest() {
        BeaconRequest request = new ApiTestBeaconRequest();
        mProvider.sendRequest(request);
    }
}
