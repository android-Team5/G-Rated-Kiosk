package com.example.g_rated_kiosk

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.g_rated_kiosk.Common.Companion.cartList
import com.example.g_rated_kiosk.databinding.ActivityMenuSelectBinding
import kotlinx.android.synthetic.main.set_or_single.*

class SetSingle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set_or_single)
        val binding = ActivityMenuSelectBinding.inflate(layoutInflater)
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

            Common.chosenMenu.drink = MenuController.FindMenu(MenuController.GetMenus(MenuType.DRINKS),"콜라");
            Common.chosenMenu.side = MenuController.FindMenu(MenuController.GetMenus(MenuType.SIDE),"케이준양념감자");
            Common.chosenMenu.count=1;
            Common.addToCart(cart(Common.chosenMenu))
            Common.chosenMenu = cart()


            var intent = Intent(this, MenuSelect::class.java);


            startActivity(intent)
            finish()
        }

        BtnBack.setOnClickListener {
            val intent = Intent(this, MenuSelect::class.java)
            startActivity(intent)
            finish()
        }
    }
}