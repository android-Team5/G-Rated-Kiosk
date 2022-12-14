package com.example.g_rated_kiosk

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
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
                del1.setBackgroundColor(Color.BLUE)
            }
            else{
                Common.chosenMenu.noLettuce = false;
                del1.setBackgroundColor(Color.parseColor("#b5b3b3"));
            }

//
        }


        del2.setOnClickListener {
            if (!Common.chosenMenu.noOnion)
            {
                Common.chosenMenu.noOnion = true;
                del2.setBackgroundColor(Color.BLUE)
            }
            else{
                Common.chosenMenu.noOnion = false;
                del2.setBackgroundColor(Color.parseColor("#b5b3b3"));
            }//            onionCnt -= 1
//


        }


        del3.setOnClickListener {
            if (!Common.chosenMenu.noPickle)
            {
                Common.chosenMenu.noPickle = true;
                del3.setBackgroundColor(Color.BLUE)
            }
            else{
                Common.chosenMenu.noPickle = false;
                del3.setBackgroundColor(Color.parseColor("#b5b3b3"));
            }//            pickleCnt -= 1
//
        }
        okButton2.setOnClickListener{
            Common.chosenMenu.count=1;
            Common.addToCart(Common.chosenMenu)
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