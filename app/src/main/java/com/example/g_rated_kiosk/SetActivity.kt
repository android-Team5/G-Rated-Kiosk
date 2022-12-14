package com.example.g_rated_kiosk

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.set.*

class SetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(/* layoutResID = */ R.layout.set)

//        burgerBtn.setOnClickListener {
//                image1.visibility = View.VISIBLE
//        }

        burgerBtn.setImageDrawable(Common.chosenMenu.menu?.MenuImage)
       // burgerText.text = Common.chosenMenu.menu!!.Name

        friesBtn.setOnClickListener {
            // Common.chosenMenu.side = null
            Common.chosenMenu.side = MenuController.FindMenu(MenuController.GetMenus(MenuType.SIDE),"치즈볼")
            image2.visibility = View.VISIBLE
        }
        cheesefriesBtn.setOnClickListener {
            image3.visibility = View.VISIBLE
        }
        cheesestickBtn.setOnClickListener {
            image4.visibility = View.VISIBLE
        }
        colepopBtn.setOnClickListener {
            image5.visibility = View.VISIBLE
        }
        colaBtn.setOnClickListener {
            image6.visibility = View.VISIBLE
        }
        ciderBtn.setOnClickListener {
            image7.visibility = View.VISIBLE
        }
        mirindaBtn.setOnClickListener {
            image8.visibility = View.VISIBLE
        }
        americanoBtn.setOnClickListener {
            image9.visibility = View.VISIBLE
        }
        okButton.setOnClickListener{
            val intent = Intent(this, MenuSelect::class.java)
            startActivity(intent)
            finish()
        }
        nextButton.setOnClickListener{
            val intent = Intent(this, SingleActivity::class.java)
            startActivity(intent)
            finish()
        }


    }


}