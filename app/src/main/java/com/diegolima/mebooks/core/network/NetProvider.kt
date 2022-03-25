package com.diegolima.mebooks.core.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetProvider {
    companion object {
        fun getRetrofitInstance(url: String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}