package com.example.g_rated_kiosk

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


class MenuView
    (context: Context?,attr: AttributeSet) : LinearLayout(context,attr) {

    var view:View

    private fun SetMenuImage(img:Drawable?){
        view.findViewById<ImageView>(R.id.menuImage).setImageDrawable(img)
    }

    private fun SetMenuName(name:String){
        view.findViewById<TextView>(R.id.menuName).text = name
    }

    private fun SetMenuPrice(price:Int){
        view.findViewById<TextView>(R.id.menuPrice).text = price.toString() + "원"
    }

    fun ClearMenu(){
        view.findViewById<LinearLayout>(R.id.menuRoot).elevation = 0.0f;
        view.findViewById<ImageView>(R.id.menuImage).setImageDrawable(null)
        view.findViewById<TextView>(R.id.menuPrice).text = ""
        view.findViewById<TextView>(R.id.menuName).text = ""
    }

    fun SetMenu(name:String, price:Int, img:Drawable?){
        SetMenuName(name)
        SetMenuImage(img)
        SetMenuPrice(price)
        view.findViewById<LinearLayout>(R.id.menuRoot).elevation = 5.0f;
    }

    init {
        view = inflate(context,R.layout.menuview,this).rootView
    }
}