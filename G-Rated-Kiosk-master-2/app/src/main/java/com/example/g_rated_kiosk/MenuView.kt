package com.example.g_rated_kiosk

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import android.widget.LinearLayout
import com.example.g_rated_kiosk.Common.Companion.chosenMenu
import com.example.g_rated_kiosk.databinding.MenuviewBinding



class MenuView
    (context: Context?, attr: AttributeSet) : LinearLayout(context!!, attr) {

    var view: MenuviewBinding
    var onClickEvent:OnClickListener? = null
    var currentMenu:Menu? = null

    private fun SetMenuImage(img: Drawable?) {
        view.menuImage.setImageDrawable(img)
    }

    private fun SetMenuName(name: String) {
        view.menuName.text = name
    }

    private fun SetMenuPrice(price: Int) {
        view.menuPrice.text = price.toString() + "원"
    }

    fun ClearMenu() {
        view.menuRoot.elevation = 0.0f;
        view.menuImage.setImageDrawable(null)
        view.menuPrice.text = ""
        view.menuName.text = ""
        currentMenu = null
        isEnabled = false
    }

    fun SetMenu(menu:Menu) {
        SetMenuName(menu.Name)
        SetMenuImage(menu.MenuImage)
        SetMenuPrice(menu.Price)
        currentMenu = menu
        view.menuRoot.elevation = 5.0f;
        isEnabled = true
    }

    override fun performClick(): Boolean {
        super.performClick()
        onClickEvent?.onClick(this)
        Log.d("testtest",currentMenu!!.Name)
         chosenMenu.menu = currentMenu

        return true
    }

    fun onClick(v:View, e:MotionEvent):Boolean{
        if(!isEnabled || currentMenu == null)
            return false
        when (e.action) {
            ACTION_DOWN -> {
                view.menuRoot.elevation = 0.0f
                view.menuRoot.translationX = 5f
                view.menuRoot.translationY = 5f
                view.menuRoot.background.setTint(Color.argb(255,198,198,198))
                Log.d("ttttt","Button Down.")
            }
            ACTION_UP -> {
                view.menuRoot.elevation = 5.0f
                view.menuRoot.translationX = 0f
                view.menuRoot.translationY = 0f
                view.menuRoot.background.setTint(Color.argb(255,255,255,255))
                Log.d("ttttt","Button Up.")

                if(isInside(v,e))
                    performClick()

            }
            ACTION_CANCEL -> {
                Log.d("ttttt","Button Cancel.")
            }
        }
        return true;
    }

    private fun isInside(v:View, e:MotionEvent):Boolean{
        return !(e.x < 0 || e.y < 0 || e.x > v.measuredWidth ||
                e.y > v.measuredHeight)
    }

    init {
        view = MenuviewBinding.inflate(LayoutInflater.from(context),this,true)
        view.menuRoot.setOnTouchListener{v,e -> onClick(v,e)}
    }

}