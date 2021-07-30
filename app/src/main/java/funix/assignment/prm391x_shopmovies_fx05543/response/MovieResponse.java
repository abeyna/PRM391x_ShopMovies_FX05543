package funix.assignment.prm391x_shopmovies_fx05543.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import funix.assignment.prm391x_shopmovies_fx05543.models.MovieModel;

/**
 * Single movie request.
 */
public class MovieResponse {
    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieModel getMovie() {
        return movie;
    };

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
