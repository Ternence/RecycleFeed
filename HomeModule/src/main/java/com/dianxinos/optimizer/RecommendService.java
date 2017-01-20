package com.dianxinos.optimizer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhangtengyuan on 2017/1/16.
 */
public interface RecommendService {

    public static final String SERVICE = "";

    @GET("/_omp/card/1.0/index?")
    Call<RecommendBean> getRecommendIndex();


    @GET("/_omp/card/1.0/detail?")
    Call<RecommendDetailsBean> getRecommendDetails();
}
