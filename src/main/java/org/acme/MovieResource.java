package org.acme;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/movies")
public class MovieResource{
    public static List<Movie> movieList = new ArrayList<>();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies(){
        return Response.ok(movieList).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/size")
    public Integer countMovies(){
        return movieList.size();
    }
    // Post method for adding new items to the list
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMovie(Movie movie){
        movieList.add(movie);
        return Response.ok(movieList).build();

    }
//    @PUT
//    @Path("{movieToUpdate}")
//    @Produces(MediaType.TEXT_PLAIN)
//    @Consumes(MediaType.TEXT_PLAIN)
//    public Response updateMovie (@PathParam("movieToUpdate") String movieToUpdate, @QueryParam("movie") String updateMovie){
//        movieList.stream().map(movie->
//        {
//            if (movie.equals(movieToUpdate)) return movieToUpdate;
//            else return movie;
//
//
//        }).collect(Collectors.toList());
//        return Response.ok(movieList).build();
//    }
    @DELETE
    @Path("{movieToDelete}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteMovie(@PathParam("movieToDelete") String movieToDelete){
        boolean removed = movieList.remove(movieToDelete);
        return removed ? Response.noContent().build():Response.status(Response.Status.BAD_REQUEST).build();
    }



}
