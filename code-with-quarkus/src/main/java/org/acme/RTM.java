package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rtm")
public class RTM {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAll(){
        List<RTM_LANGUAGE> m = RTM_LANGUAGE.listAll();

        return Response.ok(m).build();
    }
}
