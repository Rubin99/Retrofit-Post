package com.example.retrofitpost

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.retrofitpost.sharedpreference.SharedPrefManager

class SecondActivity : AppCompatActivity() {

    private lateinit var tvDisplayEmail: TextView

    /*var sharedPref: SharedPreferences? = null
    var sharedPrefEditor: SharedPreferences.Editor? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tvDisplayEmail = findViewById(R.id.tvDisplayEmail)

        /*sharedPref = getSharedPreferences("SharedPrefFile", MODE_PRIVATE);
        checkLogin();*/
    }
    override fun onStart() {
        super.onStart()

        if (!SharedPrefManager.getInstance(this).isLoggedIn) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}