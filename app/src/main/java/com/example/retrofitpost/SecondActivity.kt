package com.example.retrofitpost

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.retrofitpost.model.LoginResponse
import com.example.retrofitpost.sharedpreference.SharedPrefManager

class SecondActivity : AppCompatActivity() {

    private lateinit var tvDisplayEmail: TextView
    private lateinit var btnLogout: Button

    /*var sharedPref: SharedPreferences? = null
    var sharedPrefEditor: SharedPreferences.Editor? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tvDisplayEmail = findViewById(R.id.tvDisplayEmail)
        btnLogout = findViewById(R.id.btnLogout)

        //sharedPref = getSharedPreferences("SharedPrefFile", MODE_PRIVATE);

        val lResponse =SharedPrefManager.getInstance(this).loginResponse
        tvDisplayEmail.text = "$lResponse"

        btnLogout.setOnClickListener {
            SharedPrefManager.getInstance(this).logoutUser()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()

        if (!SharedPrefManager.getInstance(this).isLoggedIn) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}