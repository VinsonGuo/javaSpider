package com.guoziwei.http;


import com.guoziwei.model.Subject;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 登录
 * http://localhost:8080/service/user/login?account=Lj&password=123456
 * <p>
 * 短信验证码
 * http://localhost:8080/service/sms/send?recNum=15002078823
 * <p>
 * <p>
 * 验证短信验证码
 * http://localhost:8080/service/sms/validate?code=1584
 * <p>
 * 绑定支付宝
 * http://localhost:8080/service/user/login?account=Lj&alipay=123456
 * Created by guoziwei on 2017/7/20.
 */

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
