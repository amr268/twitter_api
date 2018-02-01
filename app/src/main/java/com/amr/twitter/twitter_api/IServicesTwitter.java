package com.amr.twitter.twitter_api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by amr on 31/01/18.
 */

public interface IServicesTwitter {

    @GET("/1.1/followers/list.json")
    Call<ResponseBody> getFollowers(@Query("user_id") long id);
}
