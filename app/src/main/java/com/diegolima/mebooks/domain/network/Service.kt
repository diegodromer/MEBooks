package com.diegolima.mebooks.domain.network

import com.diegolima.mebooks.domain.models.Book
import com.diegolima.mebooks.domain.models.EditorialBooks
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("book?titulo=all")
    fun getBooks(): Call<EditorialBooks>

    @GET("book")
    fun getBook(@Query("isbn", encoded = true) id: String): Call<EditorialBooks>
}