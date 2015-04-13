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
import ua.group06.persistence.File;

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
    public Response update(FileUpdate update) {
        //TODO: this is temporary method. This will be replaced with something more secure.
        fileService.testUpdate(update.getFileId(), update.getUserId(), update.getContent());
        return Response.ok().build();
    }
    
}
