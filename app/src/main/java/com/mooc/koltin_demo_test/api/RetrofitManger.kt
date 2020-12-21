package com.mooc.koltin_demo_test.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class RetrofitManger private constructor() {

    companion object {

        val instance: RetrofitManger by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Instance.getinstance()
        }

        private val BaseUrl="https://api.sunofbeach.net"

          val retrifit=Retrofit.Builder()
              .baseUrl(BaseUrl)
              .addConverterFactory(GsonConverterFactory.create())
              .build()

          val Api=retrifit.create(Net::class.java)
    }

    private object Instance {
        fun getinstance(): RetrofitManger {
            return RetrofitManger()
        }
    }
}