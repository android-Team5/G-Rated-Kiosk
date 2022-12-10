package com.example.g_rated_kiosk

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout

class MenuAdapter(val menuViews:MutableList<MenuView>, val context: Context?) : BaseAdapter(){
    override fun getCount(): Int {
        return menuViews.size
    }

    override fun getItem(p0: Int): Any {
        return menuViews[p0]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return convertView ?: LinearLayout(context)
    }

}