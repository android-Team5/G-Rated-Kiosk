package com.example.g_rated_kiosk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.single.*

var count1 = 1
var count2 = 1
var count3 = 1
class SingleActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(/* layoutResID = */ R.layout.single)

        add1.setOnClickListener {
            if(count1 == 1) {
                extraPrice1.text = "+ 400원"
                count1 ++
            }
            else if(count1 == 2)
            {
                extraPrice1.text = "+ 800원"
                count1 ++
            }
            else if(count1 == 3)
            {
                extraPrice1.text = "+ 1200원"
                count1 ++
            }
        }

        add2.setOnClickListener {
            if(count2 == 1) {
                extraPrice2.text = "+ 400원"
                count2 ++
            }
            else if(count2 == 2)
            {
                extraPrice2.text = "+ 800원"
                count2 ++
            }
            else if(count2 == 3)
            {
                extraPrice2.text = "+ 1200원"
                count2 ++
            }
        }

        add3.setOnClickListener {
            if(count3 == 1) {
                extraPrice3.text = "+ 400원"
                count3 ++
            }
            else if(count3 == 2)
            {
                extraPrice3.text = "+ 800원"
                count3 ++
            }
            else if(count3 == 3)
            {
                extraPrice3.text = "+ 1200원"
                count3 ++
            }
        }

        del1.setOnClickListener {
            count1 -= 1
            if(count1 == 1) {
                extraPrice1.text = "+ 400원"
            }
            else if(count1 == 2)
            {
                extraPrice1.text = "+ 800원"
            }
            else if(count1 == 0)
            {
                extraPrice1.text = "+ 0원"
            }
        }


        del2.setOnClickListener {
            count2 -= 1

            if(count2 == 1) {
                extraPrice2.text = "+ 400원"

            }
            else if(count2 == 2)
            {
                extraPrice2.text = "+ 800원"
            }
            else if(count2 == 0)
            {
                extraPrice2.text = "+ 0원"
            }

        }


        del3.setOnClickListener {
            count3 -= 1
            if(count3 == 1) {
                extraPrice3.text = "+ 400원"
            }
            else if(count3 == 2)
            {
                extraPrice3.text = "+ 800원"
            }
            else if(count3 == 0)
            {
                extraPrice3.text = "+ 0원"
            }

        }
        okButton2.setOnClickListener{
            val intent = Intent(this, MenuSelect::class.java)
            startActivity(intent)
            finish()
        }
        backButton2.setOnClickListener{
            val intent = Intent(this, SetSingle::class.java)
            startActivity(intent)
            finish()
        }


       
    }

}