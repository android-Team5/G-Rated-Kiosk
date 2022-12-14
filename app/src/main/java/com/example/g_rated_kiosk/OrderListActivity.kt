package com.example.g_rated_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.g_rated_kiosk.databinding.OrderListConfirmationBinding
import com.example.g_rated_kiosk.databinding.PaymentMethodBinding

class OrderListActivity : AppCompatActivity() {



    lateinit var binding: OrderListConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        binding = OrderListConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sum:Int = 0
        for(t in Common.cartList){
            sum += t.getPrice()
        }

        binding.purchaseListRecycle.layoutManager = LinearLayoutManager(this)
        binding.purchaseListRecycle.adapter = PurchaseListAdapter(Common.cartList)


        binding.totalCost.text = sum.toString()

        binding.purchaseButton.setOnClickListener {
            startActivity(Intent(this,PaymentSelection::class.java))
        }

        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this,MenuSelect::class.java))
            finish()
        }

    }






}