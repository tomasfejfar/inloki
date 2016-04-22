package com.hackaton.inloki.ws;

import com.hackaton.inloki.ws.request.PathElementsRequest;
import com.hackaton.inloki.ws.request.PathsByBeaconRequest;
import com.hackaton.inloki.ws.response.PathDetailResponse;
import com.hackaton.inloki.ws.response.PathsResponse;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by zruzicka on 22.04.2016.
 */
public class ResponseModelProvider {

    private final ServerDataProvider dataProvider;

    private final ObjectMapper mapper = new ObjectMapper();

    public ResponseModelProvider(ServerDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public PathsResponse loadAvailablePaths(PathsByBeaconRequest pathsRequest) {
        PathsResponse result;
        String response = dataProvider.sendParametrizedRequest(pathsRequest);
        try {
            String[] paths = mapper.readValue(response, String[].class);
            result = new PathsResponse(paths);
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public PathDetailResponse loadPathDetail(PathElementsRequest pathsRequest) {
        PathDetailResponse result;
        String response = dataProvider.sendParametrizedRequest(pathsRequest);
        try {
            result = mapper.readValue(response, PathDetailResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }
}
