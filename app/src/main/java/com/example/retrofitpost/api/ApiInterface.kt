package com.example.retrofitpost.api

import com.example.retrofitpost.model.LoginResponse
import com.example.retrofitpost.model.LoginResponseData
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("login")
    fun login (
        @Body requestBody: RequestBody
    ): retrofit2.Call<LoginResponse>
}