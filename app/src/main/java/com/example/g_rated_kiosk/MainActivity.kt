package com.example.g_rated_kiosk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import com.example.g_rated_kiosk.databinding.TestPageBinding

class MainActivity : AppCompatActivity() {

    // 처음 메뉴 등록
    fun initMenu(){
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸이버거",6500,getDrawable(R.drawable.psy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"간장 마늘 싸이버거",7700,getDrawable(R.drawable.gpsy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"쉬림프 싸이 플렉스버거",9900,getDrawable(R.drawable.shbu_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸이버거2",6400,getDrawable(R.drawable.psy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸이버거3",6300,getDrawable(R.drawable.psy_b)))

        MenuController.AddMenu(Menu(MenuType.DRINKS,"콜라",1500,getDrawable(R.drawable.cola_d)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"사이다",1500,getDrawable(R.drawable.cider_d)))

        MenuController.AddMenu(Menu(MenuType.SIDE,"콘샐러드",3000,getDrawable(R.drawable.cs_s)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"치즈볼",2000,getDrawable(R.drawable.cheeseball_s)))

    }

    //메뉴판의 한 칸을 설정
    private fun SetMenu(index:Int, menus:List<Menu>, menu:MenuView){
        if(menus.size<=index){
            menu.ClearMenu()
            return
        }

        menu.SetMenu(menus[index].Name, menus[index].Price,menus[index].MenuImage)
        return
    }

    private fun SetAllMenus(menus:List<Menu>, grid:GridLayout){
        for ((i, l) in grid.children.toList().withIndex()){
            SetMenu(i,menus,(l as MenuView))
        }
    }

    private fun ChangeMenuType(grid:GridLayout, type:MenuType){
        SetAllMenus(MenuController.GetMenus(type),grid)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = TestPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initMenu()

        val grid = binding.grid

        binding.burgerButton.setOnClickListener{
            ChangeMenuType(grid,MenuType.BURGER)
        }

        binding.sideButton.setOnClickListener{
            ChangeMenuType(grid,MenuType.SIDE)
        }

        binding.colaButton.setOnClickListener{
            ChangeMenuType(grid,MenuType.DRINKS)
        }

        ChangeMenuType(binding.grid,MenuType.BURGER)


    }
}