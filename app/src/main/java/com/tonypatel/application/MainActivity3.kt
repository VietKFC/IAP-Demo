package com.tonypatel.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.android.billingclient.api.*

class MainActivity3 : AppCompatActivity() {
    //Khai bao
    private lateinit var billingClient: BillingClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //Khoi tao
        billingClient = BillingClient.newBuilder(this)
            .enablePendingPurchases()
            .setListener { _, _ ->
            }
            .build()

        inAppPurchase()
    }

    private fun inAppPurchase() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                // danh sach product ID
                val skuList = listOf(
                    "product_2",
                    "product_3",
                    "product_4",
                    "product_5",
                    "product_6",
                )

                // Nếu là sub -> Đổi thành BillingClient.Skutype.SUBS
                val params = SkuDetailsParams.newBuilder().setSkusList(skuList)
                    .setType(BillingClient.SkuType.INAPP).build()
                billingClient.querySkuDetailsAsync(
                    params
                ) { billingResult, skuDetailsList ->
                    val button1 = findViewById<Button>(R.id.button_product_1)
                    val button2 = findViewById<Button>(R.id.button_product_2)
                    val button3 = findViewById<Button>(R.id.button_product_3)
                    val button4 = findViewById<Button>(R.id.button_product_4)

                    // 1 list 5 phần tử sẽ được xếp từ 0...4
                    button1.setOnClickListener {
                        skuDetailsList?.let {
                            val billingFlowParams = BillingFlowParams.newBuilder()
                                .setSkuDetails(it[0])
                                .build()
                            billingClient.launchBillingFlow(this@MainActivity3, billingFlowParams)
                        }
                    }

                    button2.setOnClickListener {
                        skuDetailsList?.let {
                            val billingFlowParams = BillingFlowParams.newBuilder()
                                .setSkuDetails(it[1])
                                .build()
                            billingClient.launchBillingFlow(this@MainActivity3, billingFlowParams)
                        }
                    }

                    button3.setOnClickListener {
                        skuDetailsList?.let {
                            val billingFlowParams = BillingFlowParams.newBuilder()
                                .setSkuDetails(it[2])
                                .build()
                            billingClient.launchBillingFlow(this@MainActivity3, billingFlowParams)
                        }
                    }

                    button4.setOnClickListener {
                        skuDetailsList?.let {
                            val billingFlowParams = BillingFlowParams.newBuilder()
                                .setSkuDetails(it[3])
                                .build()
                            billingClient.launchBillingFlow(this@MainActivity3, billingFlowParams)
                        }
                    }
                }
            }

            override fun onBillingServiceDisconnected() {
            }
        })
    }
}