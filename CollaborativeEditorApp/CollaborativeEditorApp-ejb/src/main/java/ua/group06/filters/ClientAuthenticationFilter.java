/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.filters;

import java.io.IOException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author matson
 */
@Provider
public class ClientAuthenticationFilter implements ClientRequestFilter, 
                                                   ClientResponseFilter {
    private final String AUTH_HEADER = "x-api-key";
    private final String AUTH_KEY = "secret";

    @Override
    public void filter(ClientRequestContext crc) throws IOException {
        addHeaderToRequest(crc);
    }

    @Override
    public void filter(ClientRequestContext crc, ClientResponseContext crc1) throws IOException {
        addHeaderToRequest(crc);
    }

    private void addHeaderToRequest(ClientRequestContext crc) {
        crc.getHeaders().putSingle(AUTH_HEADER, AUTH_KEY);
    }

}
