package com.murali.rxjavaproject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Murali on 08/06/2016.
 */

public interface NetworkCall {
    String BASE_URL = "https://api.cinemalytics.com/v1/movie/";
    String AUTH_TOKEN = "BDD329892D675328F6B7970EAFD70A83";

    @GET("year/{year}")
    Observable<List<Movie>> getMovies(@Path("year") String year, @Query("auth_token") String token);

    @GET("title/")
    Observable<List<Movie>> search(@Query("value") String value, @Query("auth_token") String token);

}
