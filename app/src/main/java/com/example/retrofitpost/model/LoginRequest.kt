package com.example.retrofitpost.model

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("email")
    val email: String? = null,

    @SerializedName("password")
    val password: String? = null
)