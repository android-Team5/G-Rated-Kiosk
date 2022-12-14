package com.example.g_rated_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.g_rated_kiosk.databinding.CardPaymentBinding
import com.example.g_rated_kiosk.databinding.GiftCardPaymentBinding
import com.example.g_rated_kiosk.databinding.OrderNumberBinding
import com.example.g_rated_kiosk.databinding.PaymentMethodBinding

class PaymentSelection : AppCompatActivity() {
    //lateinit var binding: ActivityMainBinding



    lateinit var binding: PaymentMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        binding = PaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancelSelectionButton.setOnClickListener {
            startActivity(Intent(this,OrderListActivity::class.java))
            finish()
        }


        binding.voucherPayment.setOnClickListener {
            val binding2 = GiftCardPaymentBinding.inflate(layoutInflater)
            setContentView(binding2.root)
            binding2.purchaseCompleteButtton.setOnClickListener{
                startActivity(Intent(this,PurchaseEndActivity::class.java))
                finish()
            }
        }

        binding.cardPayment.setOnClickListener{
            val binding2 = CardPaymentBinding.inflate(layoutInflater)
            setContentView(binding2.root)
            binding2.purchaseCompleteButtton.setOnClickListener{
                startActivity(Intent(this,PurchaseEndActivity::class.java))
                finish()
            }
        }
    }






}