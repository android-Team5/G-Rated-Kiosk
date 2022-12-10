package com.example.g_rated_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.g_rated_kiosk.databinding.ActivityMainBinding

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
        binding.toTestButton.setOnClickListener {
            val intent = Intent(this, DataManageTestPage::class.java)
            startActivity(intent)
            finish()
        }
    }
}