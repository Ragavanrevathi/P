package org.acme;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/movies")
public class MovieRes {
     @GET
     @Produces(MediaType.APPLICATION_JSON)
     public Response getAll(){
         List<Movie> m = Movie.listAll();

          return Response.ok(m).build();
     }
     @GET
     @Path("{id}")
     @Produces(MediaType.APPLICATION_JSON)

     public Response getById(@PathParam("id") Long id){
            return Movie.findByIdOptional(id)
                    .map(movie -> Response.ok(movie).build())
                    .orElse(Response.status(Response.Status.NOT_FOUND).build());
     }

    @GET
    @Transactional
    @Path("country/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
     public Response getByCountry(@PathParam("country") String country){
            List<Movie> movies =  Movie.list("SELECT m FROM Movie where m.country = ?1 oreder by id"+"desc",country);
            return Response.ok(movies).build();
     }

     @POST
     @Transactional
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
     public Response create(Movie movie){
            Movie.persist(movie);
            if(movie.isPersistent()){
                return Response.created(URI.create("/movies"+movie.id)).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
     }



}
