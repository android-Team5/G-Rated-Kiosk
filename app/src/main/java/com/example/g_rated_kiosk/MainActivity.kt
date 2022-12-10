package com.example.g_rated_kiosk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.g_rated_kiosk.databinding.ActivityMainBinding
import com.example.g_rated_kiosk.databinding.ItemBinding
class MyViewHolder (val binding: ItemBinding):RecyclerView.ViewHolder(binding.root)

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toMainButton.setOnClickListener {
            val intent = Intent(this, MenuSelect::class.java)
            startActivity(intent)
            finish()
        }
    }
}