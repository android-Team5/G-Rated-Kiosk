package com.example.g_rated_kiosk

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.single.*


class SingleActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(/* layoutResID = */ R.layout.single)



        del1.setOnClickListener {
            if (!Common.chosenMenu.noLettuce)
            {
                Common.chosenMenu.noLettuce = true;
                del1.setBackgroundColor(Color.parseColor("#fdd835"))
                noLettuce.visibility=VISIBLE;
            }
            else{
                Common.chosenMenu.noLettuce = false;
                del1.setBackgroundColor(Color.parseColor("#f5f5f5"));
                noLettuce.visibility=INVISIBLE;
            }

//
        }


        del2.setOnClickListener {
            if (!Common.chosenMenu.noOnion)
            {
                Common.chosenMenu.noOnion = true;
                del2.setBackgroundColor(Color.parseColor("#fdd835"))
                noOnion.visibility= VISIBLE;
            }
            else{
                Common.chosenMenu.noOnion = false;
                del2.setBackgroundColor(Color.parseColor("#f5f5f5"));
                noOnion.visibility = INVISIBLE;
            }//            onionCnt -= 1
//


        }


        del3.setOnClickListener {
            if (!Common.chosenMenu.noPickle)
            {
                Common.chosenMenu.noPickle = true;
                del3.setBackgroundColor(Color.parseColor("#fdd835"))
                noPickle.visibility= VISIBLE;
            }
            else{
                Common.chosenMenu.noPickle = false;
                del3.setBackgroundColor(Color.parseColor("#f5f5f5"));
                noPickle.visibility= INVISIBLE
            }//            pickleCnt -= 1
//
        }
        okButton2.setOnClickListener{
            Common.chosenMenu.count=1;
            Common.addToCart(cart(Common.chosenMenu))
            Common.chosenMenu = cart()

            val intent = Intent(this, MenuSelect::class.java)
            startActivity(intent)
            finish()
        }
        backButton2.setOnClickListener{
            var intent = Intent(this, SetSingle::class.java)
            if(Common.chosenMenu.side!=null) {
                intent = Intent(this, SetActivity::class.java)
            }
            startActivity(intent)
            finish()
        }


       
    }

}