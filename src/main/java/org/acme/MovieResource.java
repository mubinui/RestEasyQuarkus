package org.acme;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/movies")
public class MovieResource{
    public static List<String> movieList = new ArrayList<>();
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMovies(){
        return Response.ok(movieList).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/size")
    public Integer countMovies(){
        return movieList.size();
    }
    // Post method for adding new items to the list
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response createMovie(String newMovies){
        movieList.add(newMovies);
        return Response.ok(movieList).build();

    }
    @PUT
    @Path("{movieToUpdate}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateMovie (@PathParam("movieToUpdate") String movieToUpdate, @QueryParam("movie") String updateMovie){
        movieList.stream().map(movie->
        {
            if (movie.equals(movieToUpdate)) return movieToUpdate;
            else return movie;


        }).collect(Collectors.toList());
        return Response.ok(movieList).build();
    }


}
