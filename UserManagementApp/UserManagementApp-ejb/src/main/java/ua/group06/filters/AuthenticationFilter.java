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
import ua.group06.logic.AuthenticationChecker;

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
      if (!validKey(crc)) crc.abortWith(unAuthorized());
  }

  @Override
  public void filter(ContainerRequestContext crc, ContainerResponseContext crc1) 
                throws IOException {
      if (!validKey(crc)) crc1.setStatus(401);
  }

  private boolean validKey(ContainerRequestContext crc) {
      String key = crc.getHeaders().getFirst("x-api-key");
      return AuthenticationChecker.isValidKey(key);
  }

  private Response unAuthorized() {
      return Response.status(401).build();
  }

}
