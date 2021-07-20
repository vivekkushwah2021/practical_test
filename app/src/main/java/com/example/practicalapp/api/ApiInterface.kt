package com.example.practicalapp.api

import com.example.practicalapp.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("users?")
    fun getUser(@Query("page") page: Int):Call<UserModel>

}