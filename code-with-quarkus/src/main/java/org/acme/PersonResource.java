package org.acme;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
 public class PersonResource {

    @GET
    public List<RTM_LANGUAGE> list() {
        return RTM_LANGUAGE.listAll();
    }







    @GET
    @Path("/search/{name}")
    public RTM_LANGUAGE search(String name) {
        return RTM_LANGUAGE.findByName(name);
    }


}