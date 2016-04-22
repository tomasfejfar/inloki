package com.hackaton.inloki.ws;

import com.hackaton.inloki.ws.request.ApiTestBeaconRequest;
import com.hackaton.inloki.ws.request.BeaconRequest;
import com.hackaton.inloki.ws.request.ParametrizedRequest;
import com.hackaton.inloki.ws.request.PathElementsRequest;
import com.hackaton.inloki.ws.request.PathsByBeaconRequest;
import com.hackaton.inloki.ws.response.PathDetailResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by zruzicka on 22.04.2016.
 */
public class SimpleServerDataProviderTest {

    public static final String RESPONSE_FAILED = "Response is not available.";
    //TODO Provide better asserts for fail check and for error detection.

    private final ServerDataProvider mProvider = new SimpleServerDataProvider();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testPathsByBeaconRequest() {
        ParametrizedRequest request = new PathsByBeaconRequest("id1");
        String response =  mProvider.sendParametrizedRequest(request);
        Assert.assertNotNull(RESPONSE_FAILED, response);
    }

    @Test
    public void testPathElementsRequest() {
        ParametrizedRequest request = new PathElementsRequest("id1");
        String response = mProvider.sendParametrizedRequest(request);
        Assert.assertNotNull(RESPONSE_FAILED, response);
    }

    @Test
    public void testApiTestRequest() {
        BeaconRequest request = new ApiTestBeaconRequest();
        String response = mProvider.sendRequest(request);
        Assert.assertNotNull(RESPONSE_FAILED, response);
    }
}
