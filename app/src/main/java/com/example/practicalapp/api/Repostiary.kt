package com.example.practicalapp.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.practicalapp.model.UserModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repostiary {

    private val client = OkHttpClient.Builder().build()
    private lateinit var apiInterface:ApiInterface
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun buildService(){
        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    fun getUser(page:Int): MutableLiveData<UserModel> {
        val data = MutableLiveData<UserModel>()
        apiInterface.getUser(page).enqueue(object :Callback<UserModel>{
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful){
                    data.postValue(response.body())
                }else{
                    data.postValue(null)
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                data.postValue(null)
            }

        })
        return data
    }

}