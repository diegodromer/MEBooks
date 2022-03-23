package com.diegolima.mebooks.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class retrofit {
    companion object {
        fun getRetrofitInstance(url: String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}