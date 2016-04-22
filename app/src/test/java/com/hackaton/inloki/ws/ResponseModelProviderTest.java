package com.hackaton.inloki.ws;

import com.hackaton.inloki.ws.request.ParametrizedRequest;
import com.hackaton.inloki.ws.request.PathElementsRequest;
import com.hackaton.inloki.ws.request.PathsByBeaconRequest;
import com.hackaton.inloki.ws.response.PathDetailResponse;
import com.hackaton.inloki.ws.response.PathsResponse;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by zruzicka on 22.04.2016.
 */
public class ResponseModelProviderTest {

    public static final String RESPONSE_MAPPING_FAILED = "Response mapping failed.";

    private final ResponseModelProvider modelProvider = new ResponseModelProvider(new SimpleServerDataProvider());

    @Test
    public void testPathsByBeaconRequest() {
        PathsByBeaconRequest request = new PathsByBeaconRequest("id1");
        PathsResponse response =  modelProvider.loadAvailablePaths(request);
        Assert.assertNotNull(RESPONSE_MAPPING_FAILED, response);
    }

    @Test
    public void testPathElementsRequest() {
        PathElementsRequest request = new PathElementsRequest("id1");
        PathDetailResponse response =  modelProvider.loadPathDetail(request);
        Assert.assertNotNull(RESPONSE_MAPPING_FAILED, response);
    }
}
