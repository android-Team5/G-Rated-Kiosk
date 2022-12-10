package com.example.g_rated_kiosk

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import androidx.core.view.children
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
        if(MenuController.isLoaded)
            return
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸이버거",6500,getDrawable(R.drawable.psyburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"갈릭바베큐치킨버거",7800,getDrawable(R.drawable.garlicbarbequechickenburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"딥치즈싸이버거",7000,getDrawable(R.drawable.deeppsycheeseburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"불고기버거",4000,getDrawable(R.drawable.bulgogiburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"불싸이버거",6800,getDrawable(R.drawable.hotpsyburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"새우불고기버거",5000,getDrawable(R.drawable.shrimpbulgogiburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"쉬림프싸이플렉스버거",7200,getDrawable(R.drawable.shrimppsyflexburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"야채통통치킨버거",6500,getDrawable(R.drawable.vegichickenburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"어메이징매콤마요버거",7700,getDrawable(R.drawable.amazinghotmayoburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"오리지널미트볼버거",6000,getDrawable(R.drawable.originermeatballburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"인크레더블버거",7700,getDrawable(R.drawable.incredibleburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"청양마요미트볼버거",7700,getDrawable(R.drawable.chmayomeatballburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"트리플딥치즈싸이버거",7500,getDrawable(R.drawable.tripledeeppsyflexburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"화이트갈릭싸이버거",6900,getDrawable(R.drawable.whitegarlicpsyburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"휠렛버거",5500,getDrawable(R.drawable.filletburger)))

        MenuController.AddMenu(Menu(MenuType.DRINKS,"콜라",1500,getDrawable(R.drawable.d_cola)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"레몬에이드",1500,getDrawable(R.drawable.d_lemonade)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"마운틴듀",1500,getDrawable(R.drawable.d_mountaindew)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"미란다",1500,getDrawable(R.drawable.d_miranda)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"사이다",1500,getDrawable(R.drawable.d_cider)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"아메리카노(HOT)",2000,getDrawable(R.drawable.d_americano_hot)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"아메리카노(ICE)",2000,getDrawable(R.drawable.d_americano_ice)))


        MenuController.AddMenu(Menu(MenuType.SIDE,"바삭크림치즈볼",2000,getDrawable(R.drawable.s_basakcheese)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"찐감자찰빵",2000,getDrawable(R.drawable.s_realpotatobread)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"찐고구마찰빵",2000,getDrawable(R.drawable.s_realsweetpotatobread)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"치즈스틱",1300,getDrawable(R.drawable.s_cheesestick)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"케이준양념감자",1000,getDrawable(R.drawable.s_kpotato)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"코울슬로",1500,getDrawable(R.drawable.s_kourslo)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"콘샐러드",1500,getDrawable(R.drawable.s_cornsalad)))

        MenuController.isLoaded = true
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