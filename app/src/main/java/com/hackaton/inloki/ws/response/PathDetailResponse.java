package com.hackaton.inloki.ws.response;

/**
 * Created by zruzicka on 22.04.2016.
 */
public class PathDetailResponse {

    private String name;

    private String description;

    private String[] waypoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(String[] waypoints) {
        this.waypoints = waypoints;
    }
}
