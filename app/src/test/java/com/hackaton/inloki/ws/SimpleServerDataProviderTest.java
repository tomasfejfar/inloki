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

    private final ServerDataProvider mProvider = new SimpleServerDataProvider();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testPathsByBeaconRequest() {
        ParametrizedRequest request = new PathsByBeaconRequest("id1");
        String response =  mProvider.sendParametrizedRequest(request);

        try {
            String[] paths = mapper.readValue(response, String[].class);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Response mapping failed.");
        }
    }

    @Test
    public void testPathElementsRequest() {
        ParametrizedRequest request = new PathElementsRequest("id1");
        String response = mProvider.sendParametrizedRequest(request);

        try {
            PathDetailResponse pathDetail = mapper.readValue(response, PathDetailResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Response mapping failed.");
        }
    }

    @Test
    public void testApiTestRequest() {
        BeaconRequest request = new ApiTestBeaconRequest();
        mProvider.sendRequest(request);
    }
}
