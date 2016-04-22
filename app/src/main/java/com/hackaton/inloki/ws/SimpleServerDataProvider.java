package com.hackaton.inloki.ws;

import com.hackaton.inloki.ws.request.BeaconRequest;
import com.hackaton.inloki.ws.request.ParametrizedRequest;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by zruzicka on 22.04.2016.
 */
public class SimpleServerDataProvider implements ServerDataProvider {

    private final String serverURL = "http://inlokiweb.azurewebsites.net/api/";

    @Override
    public String sendRequest(BeaconRequest request) {
        return sendRestRequest(request.getRequestPart());
    }

    @Override
    public String sendParametrizedRequest(ParametrizedRequest request) {
        return sendRestRequest(request.getRequestPart() + "/" + request.getParameter());
    }

    private String sendRestRequest(String requestPath) {
        String requestURL = serverURL + requestPath;
        System.out.println("Request to server: " + requestURL);
        Client client = Client.create();
        WebResource webResource = client.resource(requestURL);

        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Unexpected response: HTTP status code: "
                    + response.getStatus());
        }

        String output = response.getEntity(String.class);

        System.out.println("Entity response from Server: " + output);
        return output;
    }
}
