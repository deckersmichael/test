/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.filters;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author matson
 */
@Provider
@PreMatching
public class AuthenticationFilter implements ContainerRequestFilter,
                                             ContainerResponseFilter {
  @Override
  public void filter(ContainerRequestContext crc) throws IOException {
      if (!validToken(crc)) crc.abortWith(unAuthorized());
  }

  @Override
  public void filter(ContainerRequestContext crc, ContainerResponseContext crc1) 
                throws IOException {
      if (!validToken(crc)) crc1.setStatus(401);
  }

  // TODO: extract this to service
  private boolean validToken(ContainerRequestContext crc) {
      String token = crc.getHeaders().getFirst("x-api-key");
      return "secret".equals(token);
  }

  private Response unAuthorized() {
      return Response.status(401).build();
  }

}
