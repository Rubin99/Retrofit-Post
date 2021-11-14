package com.example.retrofitpost.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName ("access_token")
    val accessToken: String? = null,

    @SerializedName ("token_type")
    val tokenType: String? = null,

    @SerializedName ("expires_in")
    val expiresIn: Int? = null,

    @SerializedName ("refresh_token")
    val refreshToken: String? = null

)
