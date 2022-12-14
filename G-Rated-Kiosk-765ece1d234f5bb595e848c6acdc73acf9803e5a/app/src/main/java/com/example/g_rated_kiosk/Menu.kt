package com.example.g_rated_kiosk

import android.content.res.loader.ResourcesLoader
import android.graphics.drawable.Drawable
import android.media.Image

enum class MenuType {BURGER, SIDE, DRINKS}

class Menu(private val mType:MenuType,private val mName:String,private var mPrice:Int) {
    var Type:MenuType = mType
    var Name:String = mName
    var Price:Int = mPrice
    var MenuImage: Drawable? = null

    public fun AddDrawable(imgSrc:Drawable?){
        MenuImage = imgSrc
    }

    constructor(mType:MenuType, mName:String, mPrice:Int, img:Drawable?):this(mType,mName,mPrice){
        AddDrawable(img)
    }

}