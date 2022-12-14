package com.example.g_rated_kiosk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.set.*

class SetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(/* layoutResID = */ R.layout.set)
        var sideCheck : MutableList<Boolean> = mutableListOf(true,false,false,false)
        var drinkCheck : MutableList<Boolean> = mutableListOf(true,false,false,false)
        image2.visibility = View.VISIBLE
        Common.chosenMenu.side = MenuController.FindMenu(MenuController.GetMenus(MenuType.SIDE),"케이준양념감자")
        image6.visibility = View.VISIBLE
        Common.chosenMenu.drink = MenuController.FindMenu(MenuController.GetMenus(MenuType.DRINKS),"콜라")
//        burgerBtn.setOnClickListener {
//                image1.visibility = View.VISIBLE
//        }

        burgerBtn.setImageDrawable(Common.chosenMenu.menu?.MenuImage)
        burgerText.text = Common.chosenMenu.menu!!.Name

        friesBtn.setOnClickListener {

            Common.chosenMenu.side = MenuController.FindMenu(MenuController.GetMenus(MenuType.SIDE),"케이준양념감자")

                image2.visibility = View.VISIBLE
                image3.visibility = View.INVISIBLE
                image4.visibility = View.INVISIBLE
                image5.visibility = View.INVISIBLE

        }
        cheesefriesBtn.setOnClickListener {

            Common.chosenMenu.side = MenuController.FindMenu(MenuController.GetMenus(MenuType.SIDE),"바삭크림치즈볼")
            image2.visibility = View.INVISIBLE
            image3.visibility = View.VISIBLE
            image4.visibility = View.INVISIBLE
            image5.visibility = View.INVISIBLE
        }
        cheesestickBtn.setOnClickListener {
            Common.chosenMenu.side = MenuController.FindMenu(MenuController.GetMenus(MenuType.SIDE),"치즈스틱")
            image2.visibility = View.INVISIBLE
            image3.visibility = View.INVISIBLE
            image4.visibility = View.VISIBLE
            image5.visibility = View.INVISIBLE
        }
        colepopBtn.setOnClickListener {
            Common.chosenMenu.side = MenuController.FindMenu(MenuController.GetMenus(MenuType.SIDE),"코울슬로")
            image2.visibility = View.INVISIBLE
            image3.visibility = View.INVISIBLE
            image4.visibility = View.INVISIBLE
            image5.visibility = View.VISIBLE
        }
        colaBtn.setOnClickListener {
            Common.chosenMenu.drink = MenuController.FindMenu(MenuController.GetMenus(MenuType.DRINKS),"콜라")
            image6.visibility = View.VISIBLE
            image7.visibility = View.INVISIBLE
            image8.visibility = View.INVISIBLE
            image9.visibility = View.INVISIBLE
        }
        ciderBtn.setOnClickListener {
            Common.chosenMenu.drink = MenuController.FindMenu(MenuController.GetMenus(MenuType.DRINKS),"사이다")
            image6.visibility = View.INVISIBLE
            image7.visibility = View.VISIBLE
            image8.visibility = View.INVISIBLE
            image9.visibility = View.INVISIBLE
        }
        mirindaBtn.setOnClickListener {
            Common.chosenMenu.drink = MenuController.FindMenu(MenuController.GetMenus(MenuType.DRINKS),"미린다")
            image6.visibility = View.INVISIBLE
            image7.visibility = View.INVISIBLE
            image8.visibility = View.VISIBLE
            image9.visibility = View.INVISIBLE
        }
        americanoBtn.setOnClickListener {
            Common.chosenMenu.drink = MenuController.FindMenu(MenuController.GetMenus(MenuType.DRINKS),"아메리카노(ICE)")
            image6.visibility = View.INVISIBLE
            image7.visibility = View.INVISIBLE
            image8.visibility = View.INVISIBLE
            image9.visibility = View.VISIBLE
        }
        backButton.setOnClickListener{
            val intent = Intent(this, SetSingle::class.java)
            startActivity(intent)
            finish()
        }
        nextButton.setOnClickListener{
            val intent = Intent(this, SingleActivity::class.java)
            startActivity(intent)
        }


    }


}