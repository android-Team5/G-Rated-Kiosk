package com.example.g_rated_kiosk

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.example.g_rated_kiosk.databinding.ActivityMainBinding
import com.example.g_rated_kiosk.databinding.ItemBinding
import com.example.g_rated_kiosk.databinding.PaymentMethodBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MyViewHolder (val binding: ItemBinding):RecyclerView.ViewHolder(binding.root)

class MainActivity : AppCompatActivity() {
    //lateinit var binding: ActivityMainBinding
    lateinit var database: DatabaseReference

    lateinit var binding: PaymentMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        binding = PaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database.reference


        binding.cardPayment.setOnClickListener{

            updateDatabase()
        }

//        binding.toMainButton.setOnClickListener {
//            val intent = Intent(this, MenuSelect::class.java)
//            startActivity(intent)
//            finish()
//        }
    }

    private fun updateDatabase() {
        val productName = "싸이버거"
        val price = 6500
        val quantitySold = 2
        val stock = 65

        val product = Product(productName, price, stock)
        val sales = Sales(productName, price, quantitySold)

        val productKey = "inventory/product/$productName/ "
        val salesKey = "inventory/sales/$productName/"

        database.child(productKey).setValue(product)
        database.child(salesKey).setValue(sales)
    }
}