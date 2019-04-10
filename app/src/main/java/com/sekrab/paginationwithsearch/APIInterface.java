package com.sekrab.paginationwithsearch;



import com.sekrab.paginationwithsearch.Model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by anupamchugh on 09/01/17.
 */

interface APIInterface {

    @GET("api/activity_feeds?&user_id=2&activity_type=1")
    Call<Model> doGetListResources(@Query("page") int page);

}
