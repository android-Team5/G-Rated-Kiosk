package com.example.g_rated_kiosk

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.g_rated_kiosk.Common.Companion.cartList
import com.example.g_rated_kiosk.Common.Companion.initiateCartList
import com.example.g_rated_kiosk.databinding.ActivityMenuSelectBinding

class MenuSelect : AppCompatActivity() {

    var currentPage:Int = 1
    var maxPage:Int = 1
    var itemPerPage:Int = 12
    var cartList = Common.cartList
    lateinit var currentType:MenuType
    lateinit var binding:ActivityMenuSelectBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    fun initMenu(){
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸이버거",6500,getDrawable(R.drawable.psy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"간장 마늘 싸이버거",7700,getDrawable(R.drawable.gpsy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"쉬림프 싸이 플렉스버거",9900,getDrawable(R.drawable.shbu_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸이버거2",6400,getDrawable(R.drawable.psy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸이버거3",6300,getDrawable(R.drawable.psy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸이버거",6500,getDrawable(R.drawable.psy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"간장h 마늘 싸이버거",7700,getDrawable(R.drawable.gpsy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"쉬gh림프 싸이 플렉스버거",9900,getDrawable(R.drawable.shbu_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸이gjhg버거2",6400,getDrawable(R.drawable.psy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸이gjhg버거3",6300,getDrawable(R.drawable.psy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸이버거",6500,getDrawable(R.drawable.psy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"간u장 마늘 싸이버거",7700,getDrawable(R.drawable.gpsy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"쉬hyghgj림프 싸이 플렉스버거",9900,getDrawable(R.drawable.shbu_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸gjhg이버거2",6400,getDrawable(R.drawable.psy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸jhgjh이버거3",6300,getDrawable(R.drawable.psy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸hhh이버거",6500,getDrawable(R.drawable.psy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"간장 마늘 싸이버거",7700,getDrawable(R.drawable.gpsy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"쉬gjhg림프 싸이 플렉스버거",9900,getDrawable(R.drawable.shbu_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸gjhgjh이버거2",6400,getDrawable(R.drawable.psy_b)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸ytuyt이버거3",6300,getDrawable(R.drawable.psy_b)))

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
        menu.SetMenu(menus[index])
        return
    }

    private fun SetAllMenus(grid: GridLayout, page:Int){
        val offset:Int = itemPerPage * (page-1)

        for ((i, l) in grid.children.toList().withIndex()){
            SetMenu(i+offset,MenuController.GetMenus(currentType),(l as MenuView))
        }
    }

    private fun ChangePage(page:Int){
        currentPage = page
        SetAllMenus(binding.grid,page)
        SetPageLabel(page)
        SetPageButtonVisibility(page)
    }

    private fun SetPageButtonVisibility(page:Int){
        if(page>1)
            binding.prevPageButton.visibility = View.VISIBLE
        else
            binding.prevPageButton.visibility = View.INVISIBLE

        if(page<maxPage)
            binding.nextPageButton.visibility = View.VISIBLE
        else
            binding.nextPageButton.visibility = View.INVISIBLE
    }

    private fun SetPageLabel(page:Int){
        binding.pageLabel.text = page.toString() + " / " + maxPage.toString()
    }

    private fun ChangeMenuType(type:MenuType){
        currentType = type
        maxPage = (MenuController.GetMenus(type).size - 1) / itemPerPage + 1
        ChangePage(1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initiateCartList()
        binding = ActivityMenuSelectBinding.inflate(layoutInflater)
        binding.recycle.layoutManager = LinearLayoutManager(this)
        binding.recycle.adapter=CartAdapter(cartList = cartList)
        itemPerPage = binding.grid.rowCount * binding.grid.columnCount

        setContentView(binding.root)
        initMenu()

        binding.burgerButton.setOnClickListener{
            ChangeMenuType(MenuType.BURGER)
        }

        binding.sideButton.setOnClickListener{
            ChangeMenuType(MenuType.SIDE)
        }

        binding.colaButton.setOnClickListener{
            ChangeMenuType(MenuType.DRINKS)
        }

        ChangeMenuType(MenuType.BURGER)

        binding.prevPageButton.setOnClickListener {
            ChangePage(currentPage-1)
        }

        binding.nextPageButton.setOnClickListener {
            ChangePage(currentPage+1)
        }

        for(t in binding.grid.children.toList()){
            (t as MenuView).onClickEvent = View.OnClickListener { v -> Log.d("tt","clicked clicked clicked") }
        }
    }
}