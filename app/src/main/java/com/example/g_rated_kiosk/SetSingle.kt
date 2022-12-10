package com.example.g_rated_kiosk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.set_or_single.*

class SetSingle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set_or_single)

        BtnSet.setOnClickListener {
            val intent = Intent(this, SetActivity::class.java)
            startActivity(intent)
            finish()
        }
            BtnSingle.setOnClickListener {
                val intent = Intent(this, SingleActivity::class.java)
                startActivity(intent)
                finish()
        }
        BtnBasic.setOnClickListener {
            val intent = Intent(this, MenuSelect::class.java)
            startActivity(intent)
            finish()
        }
    }
}