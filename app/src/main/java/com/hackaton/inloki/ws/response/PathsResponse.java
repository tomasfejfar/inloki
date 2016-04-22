package com.hackaton.inloki.ws.response;

import java.util.List;

/**
 * Created by zruzicka on 22.04.2016.
 */
public class PathsResponse {

    private final String[] paths;

    public PathsResponse(String[] paths) {
        this.paths = paths;
    }

    public String[] getPaths() {
        return paths;
    }

}
