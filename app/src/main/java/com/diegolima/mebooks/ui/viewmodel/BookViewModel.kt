package com.diegolima.mebooks.ui.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegolima.mebooks.core.retrofit
import com.diegolima.mebooks.domain.models.Book
import com.diegolima.mebooks.domain.models.EditorialBooks
import com.diegolima.mebooks.domain.network.Service
import com.diegolima.mebooks.ui.adapter.EditorialBookViewAdapter
import com.diegolima.mebooks.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewModel : ViewModel() {
    var liveData: MutableLiveData<EditorialBooks> = MutableLiveData()
    var liveDataId: MutableLiveData<EditorialBooks> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<EditorialBooks>{
        return liveData
    }

    fun getLiveDataIdObserver(): MutableLiveData<EditorialBooks>{
        return liveDataId
    }

    fun makeApiCall(){
        val retroInstance = retrofit.getRetrofitInstance(Constants.BASE_URL)
        val retroService = retroInstance.create(Service::class.java)
        val call = retroService.getBooks()
        call.enqueue(object : Callback<EditorialBooks>{
            override fun onResponse(
                call: Call<EditorialBooks>,
                response: Response<EditorialBooks>
            ) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<EditorialBooks>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

    fun makeApiCallId(book: String) {
        val retrofitClient =
            retrofit.getRetrofitInstance(Constants.BASE_URL)
        val requestEndpoint = retrofitClient.create(Service::class.java)

        requestEndpoint.getBook(book)
            .enqueue(object : Callback<EditorialBooks> {
                override fun onResponse(
                    call: Call<EditorialBooks>,
                    response: Response<EditorialBooks>
                ) {
                    if (!response.isSuccessful) {
                        liveDataId.postValue(null)
                    } else {
                        liveDataId.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<EditorialBooks>, t: Throwable) {
                    println("Erro na requisição")
                }
            })
    }
}