package com.hackaton.inloki.ws;

import com.hackaton.inloki.ws.request.BeaconRequest;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by zruzicka on 22.04.2016.
 */
public class SimpleServerDataProvider implements ServerDataProvider {

    private final String serverURL = "http://inlokiweb.azurewebsites.net/api/";

    @Override
    public void sendBeaconRequest(BeaconRequest request) {
        sendRequest(request);
    }

    protected void sendRequest(BeaconRequest request)  {
        Client client = Client.create();

        String requestUrl = serverURL + request.getRequestPart();
        System.out.println("Request to server: " + requestUrl);
        WebResource webResource = client.resource(requestUrl);

        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Unexpected response: HTTP status code: "
                    + response.getStatus());
        }

        String output = response.getEntity(String.class);

        System.out.println("Entity response from Server: " + output);
    }
}
