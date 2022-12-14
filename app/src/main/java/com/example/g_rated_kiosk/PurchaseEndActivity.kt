package com.example.g_rated_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import com.example.g_rated_kiosk.DataManage.StocksManager
import com.example.g_rated_kiosk.databinding.CardPaymentBinding
import com.example.g_rated_kiosk.databinding.GiftCardPaymentBinding
import com.example.g_rated_kiosk.databinding.OrderNumberBinding
import com.example.g_rated_kiosk.databinding.PaymentMethodBinding
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class PurchaseEndActivity : AppCompatActivity() {
    //lateinit var binding: ActivityMainBinding



    lateinit var binding: OrderNumberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OrderNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StocksManager.sellStock(Common.cartList)

        GlobalScope.launch() {
            delay(3000L)
            Common.cartList.clear()
            startActivity(Intent(this@PurchaseEndActivity,MenuSelect::class.java))
            finish()
        }

    }
}