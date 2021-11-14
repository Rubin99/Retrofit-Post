package com.example.retrofitpost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.retrofitpost.api.RetrofitHelper
import com.example.retrofitpost.model.LoginRequest
import com.example.retrofitpost.model.LoginResponse
import com.example.retrofitpost.model.LoginResponseData
import com.google.gson.GsonBuilder
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var btnLogin: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin?.setOnClickListener {
           loginUser()
        }

    }

    private fun loginUser() {
        if (validateFields()){
            implementLoginApi()
        }
    }

    private fun implementLoginApi() {
        val json = GsonBuilder().create().toJson(LoginRequest(etEmail?.text.toString(), etPassword?.text.toString()))
        val applicationJson = "application/json".toMediaTypeOrNull()
        val request = json.toRequestBody(applicationJson)

        RetrofitHelper.getApiInterface().login(request).enqueue(object : Callback<LoginResponseData?> {
            override fun onResponse(
                call: Call<LoginResponseData?>,
                response: Response<LoginResponseData?>
            ) {
                startActivity(Intent(this@MainActivity, SecondActivity::class.java))
                finish()
            }

            override fun onFailure(call: Call<LoginResponseData?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "THIS NOT WORKING G", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun validateFields(): Boolean {
        if (etEmail?.text.isNullOrEmpty()){
            Toast.makeText(this, "Please type in the Email", Toast.LENGTH_LONG).show()
            return false
        }
        else if(etPassword?.text.isNullOrEmpty()){
            Toast.makeText(this, "Please type in the password", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}