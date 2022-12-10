package com.example.g_rated_kiosk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.g_rated_kiosk.databinding.ItemBinding
import com.example.g_rated_kiosk.databinding.PaymentMethodBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MyViewHolder (val binding: ItemBinding):RecyclerView.ViewHolder(binding.root)

class MainActivity : AppCompatActivity() {
    //lateinit var binding: ActivityMainBinding



    lateinit var binding: PaymentMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        binding = PaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.cardPayment.setOnClickListener{

            updateSales(
                productName = "쉬림프 싸이 플렉스버거",
                quantity = 5,
                price = 9900,

            )

            addCurrentStock(
                productName = "쉬림프 싸이 플렉스버거",
                quantity = 5,
                price = 9900,

            )

            addCurrentStock(
                productName = "치즈볼",
                quantity = 20,
                price = 2000,

            )

            addIncomingStock(
                productName = "쉬림프 싸이 플렉스버거",
                quantity = 30,
                price = 9900,

            )
            Log.d("CurrentStock", "added current stock")

        }

//        binding.toMainButton.setOnClickListener {
//            val intent = Intent(this, MenuSelect::class.java)
//            startActivity(intent)
//            finish()
//        }
    }






}