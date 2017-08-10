package com.guoziwei.http;


import com.guoziwei.model.Subject;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface HttpService {

    @GET
    Observable<String> getHtml(@Url String url);

    /**
     * 正在热映
     */
    @GET("/v2/movie/in_theaters?count=50")
    Observable<Subject> getInTheaters(@Query("start") int start);

    /**
     * 正在热映
     */
    @GET("/v2/movie/top250?count=50")
    Observable<Subject> getTop250(@Query("start") int start);
}
