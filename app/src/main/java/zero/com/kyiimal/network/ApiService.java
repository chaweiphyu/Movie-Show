package zero.com.kyiimal.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import zero.com.kyiimal.data.vos.MovieDetailResponseVO;
import zero.com.kyiimal.data.vos.MovieResponseVO;
import zero.com.kyiimal.data.vos.MovieTailerResponseVO;

public interface ApiService {

    @GET("3/movie/now_playing")
    Call<MovieResponseVO> getNowPlayingMovie(
            @Query("api_key")String apiKey,
            @Query("language")String language,
            @Query("page")int page
    );

    @GET("3/movie/popular")
    Call<MovieResponseVO> getPopularMovie(
            @Query("api_key")String apiKey,
            @Query("language")String language,
            @Query("page")int page
    );

    @GET("3/movie/top_rated")
    Call<MovieResponseVO> getTopMovie(
            @Query("api_key")String apiKey,
            @Query("language")String language,
            @Query("page")int page
    );

    @GET("3/movie/upcoming")
    Call<MovieResponseVO> getUpcoming(
            @Query("api_key")String apiKey,
            @Query("language")String language,
            @Query("page")int page
    );


    @GET("3/movie/{movie_id}")
    Call<MovieDetailResponseVO> getNowMovieDetails(
            @Path("movie_id")long movieId,
            @Query("api_key")String apiKey,
            @Query("language")String language
    );

    @GET("3/movie/{movie_id}/videos")
    Call<MovieTailerResponseVO> getMovieTrailer(
            @Path("movie_id")long movieId,
            @Query("api_key")String apiKey,
            @Query("language")String language

    );



}
