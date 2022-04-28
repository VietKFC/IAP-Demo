package com.tonypatel.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

import androidx.annotation.NonNull
import com.android.billingclient.api.*
import com.android.billingclient.api.SkuDetailsParams
import com.android.billingclient.api.BillingFlowParams
import android.widget.Toast

import com.android.billingclient.api.BillingClient

import com.android.billingclient.api.SkuDetails

import com.android.billingclient.api.BillingResult

import com.android.billingclient.api.SkuDetailsResponseListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button_1)
        val button2 = findViewById<Button>(R.id.button_2)
        val button3 = findViewById<Button>(R.id.button_3)

        button1.setOnClickListener {
            goToMainActivity2()
        }

        button2.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }

        button3.setOnClickListener {
            startActivity(Intent(this, MainActivity4::class.java))
        }
    }

    fun goToMainActivity2() {
        startActivity(Intent(this, MainActivity2::class.java))
    }
}