package org.acme;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    @PUT
    @Path("{id}/{title}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateMovie (@PathParam("id") Long id, @PathParam("title") String title){

        movieList.stream().map(movie->
        {
            if (movie.getId()==id){
                movie.setTitle(title);
                return movie;
            }
            else return movie;

        }).collect(Collectors.toList());
        return Response.ok(movieList).build();
    }
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMovie(@PathParam("id") Long id ){
        Optional<Movie> movieToDelete = movieList.stream().filter(movie -> movie.getId().equals(id)).findFirst();
        boolean removed = false;
        if(movieToDelete.isPresent()) removed = movieList.remove(movieList.remove(movieToDelete));
        if (removed) return Response.noContent().build(); // Suppose to happen
        return Response.status(Response.Status.BAD_REQUEST).build();

    }


}
