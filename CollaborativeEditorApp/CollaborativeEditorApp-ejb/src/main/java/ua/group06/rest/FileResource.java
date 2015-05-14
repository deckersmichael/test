/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import ua.group06.entities.FileUpdate;
import ua.group06.logic.FileServiceLocal;

/**
 *
 * @author matson
 */
@Stateless
@Path("files")
public class FileResource extends RestResource {
    @EJB
    private FileServiceLocal fileService;
    
    @POST
    @Path("update")
    // TODO: handle errors
    public Response update(FileUpdate changes) {
        String content = fileService.updateContent(changes.getFileId(), changes.getToken(), changes.getBrowserID(), changes.getContent(), changes.getChanges(), changes.getTimeDate());
        return Response.ok(content).build();
    }

}
