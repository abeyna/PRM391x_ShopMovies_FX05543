package funix.assignment.prm391x_shopmovies_fx05543.utils;

import funix.assignment.prm391x_shopmovies_fx05543.response.MovieSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    // Search for movies
//    https://api.themoviedb.org/3/search/movie?api_key=270a8071656dfc411abbdf3c0e310219&query=Jack+Reacher
    @GET
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page
    );
}
