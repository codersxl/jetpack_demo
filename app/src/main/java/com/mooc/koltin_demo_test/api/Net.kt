package com.mooc.koltin_demo_test.api

import com.mooc.koltin_demo_test.ben.BaseRelt
import com.mooc.koltin_demo_test.ben.HomeItem
import retrofit2.http.GET
import retrofit2.http.Path

public interface Net {

    @GET("/shop/onSell/{page}")
    suspend fun getHome(@Path("page") page:Int):BaseRelt<HomeItem>


}