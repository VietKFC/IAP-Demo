package com.tonypatel.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.android.billingclient.api.*

class MainActivity4 : AppCompatActivity() {
    private lateinit var billingClient: BillingClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        billingClient = BillingClient.newBuilder(this)
            .enablePendingPurchases()
            .setListener { _, _ ->
            }
            .build()

        getSubscription()
    }

    private fun getSubscription() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                val skuList = listOf(
                    "subscription_1"
                )
                val params = SkuDetailsParams.newBuilder().setSkusList(skuList)
                    .setType(BillingClient.SkuType.SUBS).build()
                val billingResult =
                    billingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS)
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    billingClient.querySkuDetailsAsync(
                        params
                    ) { billingResult, skuDetailsList ->
                        val button1 = findViewById<Button>(R.id.button_product_1)

                        button1.setOnClickListener {
                            skuDetailsList?.let {
                                if (it.isNotEmpty()) {
                                    val billingFlowParams = BillingFlowParams.newBuilder()
                                        .setSkuDetails(it[0])
                                        .build()
                                    billingClient.launchBillingFlow(
                                        this@MainActivity4,
                                        billingFlowParams
                                    )
                                }
                            }
                        }
                    }
                }
            }

            override fun onBillingServiceDisconnected() {
            }
        })
    }
}