package com.zoomcar.resources;


import com.zoomcar.impl.CarGroupManagerImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * since 1.0.0
 *
 * @author Abhishek Tyagi on 16/05/16.
 */
@Path("/inventory/cargroups")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarGroupResource {

    private CarGroupManagerImpl carGroupManager = CarGroupManagerImpl.getInstance();

    @GET
    @Path("/{id}")
    public Response getCarGroup(@PathParam("id") int id) {
        return Response.ok(carGroupManager.getCarGroup(id)).build();
    }


}
