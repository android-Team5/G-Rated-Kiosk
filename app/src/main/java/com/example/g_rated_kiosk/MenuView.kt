package com.example.g_rated_kiosk

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridLayout
import com.example.g_rated_kiosk.databinding.MenuviewBinding


class MenuView: GridLayout{

    constructor(context: Context?):super(context){
        inflate(context,R.layout.menupreview,this)
    }
}