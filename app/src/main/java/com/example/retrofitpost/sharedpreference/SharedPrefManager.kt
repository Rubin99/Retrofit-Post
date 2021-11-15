package com.example.retrofitpost.sharedpreference

import android.content.Context
import com.example.retrofitpost.model.LoginResponse

class SharedPrefManager (private val context: Context) {
    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
            return sharedPreferences.getInt("expiresIn", -1) != -1
        }

    val loginResponse: LoginResponse
    get() {
        val sharedPreferences = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        return LoginResponse(
            sharedPreferences.getString("accessToken", null)!!,
            sharedPreferences.getString("tokenType", null)!!,
            sharedPreferences.getInt("expiresIn", -1)!!,
            sharedPreferences.getString("refreshToken", null)!!
        )
    }

    val displayLogin: LoginResponse
    get() {
        val sharedPreferences = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        return LoginResponse(

            sharedPreferences.getInt("expiresIn", -1).toString()
        )
    }

    fun saveUser(loginResponse: LoginResponse){
        val sharedPreferences = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("accessToken", loginResponse.accessToken)
        editor.putString("tokenType", loginResponse.tokenType)
        editor.putInt("expiresIn", loginResponse.expiresIn!!)
        editor.putString("refreshToken", loginResponse.refreshToken)

        editor.apply()
        editor.commit()

    }

    fun logoutUser(){
        val sharedPreferences = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.clear()
        editor.commit()
    }

    companion object {
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(context: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(context)
            }
            return mInstance as SharedPrefManager
        }
    }

}