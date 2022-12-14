package com.example.g_rated_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.g_rated_kiosk.databinding.FhtgBinding

class FromHereToGo : AppCompatActivity() {
    var eatWhere=""
    var logoClick=0
    override fun onCreate(savedInstanceState: Bundle?) {

        val fhtgBinding = FhtgBinding.inflate(layoutInflater)


        super.onCreate(savedInstanceState)
        setContentView(fhtgBinding.root)



        fhtgBinding.fromHere.setOnClickListener {

            eatWhere = "fromHere"
            val intent = Intent(this, MenuSelect::class.java)
            startActivity(intent);
        }
        fhtgBinding.toGo.setOnClickListener {
            eatWhere = "toGo"
            val intent = Intent(this, MenuSelect::class.java)
            startActivity(intent);
        }
        fhtgBinding.toGo.setOnLongClickListener {


            if (logoClick>=5){
                logoClick=0;
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent);
            }

            return@setOnLongClickListener true;
        }
        fhtgBinding.logo.setOnClickListener {

                logoClick +=1
        }


    }
}