package com.example.g_rated_kiosk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.single.*


class SingleActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(/* layoutResID = */ R.layout.single)

        var lettuceCnt = 1
        var onionCnt = 1
        var pickleCnt = 1

        add1.setOnClickListener {
            if(lettuceCnt == 1) {
                extraPrice1.text = "+ 400원"
                lettuceCnt ++
            }
            else if(lettuceCnt == 2)
            {
                extraPrice1.text = "+ 800원"
                lettuceCnt ++
            }
            else if(lettuceCnt == 3)
            {
                extraPrice1.text = "+ 1200원"
                lettuceCnt ++
            }
        }

        add2.setOnClickListener {
            if(onionCnt == 1) {
                extraPrice2.text = "+ 400원"
                onionCnt ++
            }
            else if(onionCnt == 2)
            {
                extraPrice2.text = "+ 800원"
                onionCnt ++
            }
            else if(onionCnt == 3)
            {
                extraPrice2.text = "+ 1200원"
                onionCnt ++
            }
        }

        add3.setOnClickListener {
            if(pickleCnt == 1) {
                extraPrice3.text = "+ 400원"
                pickleCnt ++
            }
            else if(pickleCnt == 2)
            {
                extraPrice3.text = "+ 800원"
                pickleCnt ++
            }
            else if(pickleCnt == 3)
            {
                extraPrice3.text = "+ 1200원"
                pickleCnt ++
            }
        }

        del1.setOnClickListener {
            lettuceCnt -= 1
            if(lettuceCnt == 1) {
                extraPrice1.text = "+ 400원"
            }
            else if(lettuceCnt == 2)
            {
                extraPrice1.text = "+ 800원"
            }
            else if(lettuceCnt == 0)
            {
                extraPrice1.text = "+ 0원"
            }
        }


        del2.setOnClickListener {
            onionCnt -= 1

            if(onionCnt == 1) {
                extraPrice2.text = "+ 400원"

            }
            else if(onionCnt == 2)
            {
                extraPrice2.text = "+ 800원"
            }
            else if(onionCnt == 0)
            {
                extraPrice2.text = "+ 0원"
            }

        }


        del3.setOnClickListener {
            pickleCnt -= 1
            if(pickleCnt == 1) {
                extraPrice3.text = "+ 400원"
            }
            else if(pickleCnt == 2)
            {
                extraPrice3.text = "+ 800원"
            }
            else if(pickleCnt == 0)
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