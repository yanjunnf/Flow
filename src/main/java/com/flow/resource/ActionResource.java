package com.flow.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.ws.rs.core.MediaType;

import com.flow.ConfigClass;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.annotations.ApiResponse;

/**
 * Root resource (exposed at "wb" path)
 */
@Path("action")
@Api(value = "ActionResource", description = "Action services")
public class ActionResource {
    @POST
    @Path("/reloadConfig")
    @ApiOperation(value = "Reload config file(config.properties + siteConfig.cfg)")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully reload config file"),})
    public Response reloadConfig() {
        return Response.status(Status.OK).entity("Reload configuration successfully").
            header("Content-Type", ConfigClass.contentType).build();
    }
}
