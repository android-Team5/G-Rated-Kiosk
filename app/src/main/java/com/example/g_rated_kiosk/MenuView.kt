package com.example.g_rated_kiosk

import android.app.Activity
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
import com.example.g_rated_kiosk.DataManage.MenuStocks
import com.example.g_rated_kiosk.databinding.MenuviewBinding


class MenuView
    (context: Context?, attr: AttributeSet) : LinearLayout(context!!, attr) {

    companion object{
        val quantityThreshold:Int = 10 // 메뉴를 판매개시할 수 있는 최소 수량. 재고가 이 수치 이하일 경우 품절처리
    }

    var view: MenuviewBinding
    var onPerformClickEvent:OnClickListener? = null
    var currentMenu:Menu? = null
    var isSoldOut = false

    fun UpdateStock(){
        isSoldOut = true
        currentMenu?.let{
            val stock = MenuStocks.find(it.Name)
            if((stock?.Stock ?: 0) <= quantityThreshold){
                isSoldOut = false
            }
        }
    }

    private fun SetSoldOut(value: Boolean){
        isSoldOut = value
        if(value){
            view.menuPrice.text = "품절"
            view.menuPrice.setTextColor(Color.RED)
        }
        else{
            view.menuPrice.text = currentMenu!!.Price.toString() + "원"
            view.menuPrice.setTextColor(R.color.black)
        }
    }

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
        UpdateStock()
    }

    override fun performClick(): Boolean {
        val intent = (context as Activity).intent

        super.performClick()


        onPerformClickEvent?.onClick(this)

        return true
    }

    fun onClick(v:View, e:MotionEvent):Boolean{
        if(!isEnabled || isSoldOut || currentMenu == null)
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