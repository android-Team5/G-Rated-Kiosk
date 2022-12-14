package com.example.g_rated_kiosk

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.g_rated_kiosk.Common.Companion.cartList
import com.example.g_rated_kiosk.DataManage.MenuStock
import com.example.g_rated_kiosk.DataManage.MenuStocks
import com.example.g_rated_kiosk.DataManage.StocksManager

import com.example.g_rated_kiosk.databinding.ActivityMenuSelectBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fhtg.*

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
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸이버거",4300,getDrawable(R.drawable.psyburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"갈릭바베큐치킨버거",5600,getDrawable(R.drawable.garlicbarbequechickenburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"딥치즈싸이버거",4800,getDrawable(R.drawable.deeppsycheeseburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"불고기버거",3500,getDrawable(R.drawable.bulgogiburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"불싸이버거",4400,getDrawable(R.drawable.hotpsyburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"새우불고기버거",5100,getDrawable(R.drawable.shrimpbulgogiburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"쉬림프싸이플렉스버거",6500,getDrawable(R.drawable.shrimppsyflexburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"야채통통치킨버거",5900,getDrawable(R.drawable.vegichickenburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"어메이징매콤마요버거",5600,getDrawable(R.drawable.amazinghotmayoburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"오리지널미트볼버거",5900,getDrawable(R.drawable.originermeatballburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"인크레더블버거",7500,getDrawable(R.drawable.incredibleburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"청양마요미트볼버거",5900,getDrawable(R.drawable.chmayomeatballburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"트리플딥치즈싸이버거",5100,getDrawable(R.drawable.tripledeeppsyflexburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"화이트갈릭싸이버거",4900,getDrawable(R.drawable.whitegarlicpsyburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"휠렛버거",4100,getDrawable(R.drawable.filletburger)))

        MenuController.AddMenu(Menu(MenuType.DRINKS,"콜라",1600,getDrawable(R.drawable.d_cola)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"레몬에이드",2200,getDrawable(R.drawable.d_lemonade)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"마운틴듀",1600,getDrawable(R.drawable.d_mountaindew)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"미란다",1600,getDrawable(R.drawable.d_miranda)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"사이다",1600,getDrawable(R.drawable.d_cider)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"아메리카노(HOT)",2000,getDrawable(R.drawable.d_americano_hot)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"아메리카노(ICE)",2000,getDrawable(R.drawable.d_americano_ice)))


        MenuController.AddMenu(Menu(MenuType.SIDE,"바삭크림치즈볼",2000,getDrawable(R.drawable.s_basakcheese)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"찐감자찰빵",3500,getDrawable(R.drawable.s_realpotatobread)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"찐고구마찰빵",3500,getDrawable(R.drawable.s_realsweetpotatobread)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"치즈스틱",2000,getDrawable(R.drawable.s_cheesestick)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"케이준양념감자",2000,getDrawable(R.drawable.s_kpotato)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"코울슬로",1800,getDrawable(R.drawable.s_kourslo)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"콘샐러드",1800,getDrawable(R.drawable.s_cornsalad)))

        val list = mutableListOf<MenuStock>()
        StocksManager.initiateStocks().addOnSuccessListener { documents ->
            Log.d("database Initiation","database load success...")
            for(s in documents){
                list.add(MenuStock(s.id, (s.data["price"] as Long).toInt(), (s.data["stock"] as Long).toInt()))
            }
            for (t in list){
                Log.d("data From Database Update",String.format("name : ${t.Name}, stock : ${t.Stock}"))
                MenuStocks.update(t)
            }
            UpdateAllMenus()
        }
            .addOnFailureListener { exception ->
                Log.e("Database Load Failed",exception.stackTraceToString())
            }

        MenuController.isLoaded = true

    }

    fun UpdateAllMenus(){
        for(item in binding.grid.children.toList()){
            (item as MenuView).UpdateStock()
        }
    }

    //메뉴판의 한 칸을 설정
    private fun SetMenu(index:Int, menus:List<Menu>, menu:MenuView){
        if(menus.size<=index){
            menu.ClearMenu()
            return
        }

        addCartAndRefresh(menu)
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

    private fun addCartAndRefresh(MenuView:MenuView){
        MenuView.onPerformClickEvent = View.OnClickListener {
            Common.chosenMenu = cart()


            if((it as MenuView).currentMenu!!.Type == MenuType.BURGER) {
                Common.chosenMenu.menu = (it as MenuView).currentMenu
                val intent = Intent(this, SetSingle::class.java)
                startActivity(intent)
            }
            else{
                Common.chosenMenu.menu = (it as MenuView).currentMenu

                Common.addToCart(Common.chosenMenu)
                Common.chosenMenu = cart()
                binding.recycle.adapter!!.notifyDataSetChanged()
                binding.recycle.scrollToPosition(cartList.size-1)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        binding.purchase.setOnClickListener{
             startActivity(Intent(this,OrderListActivity::class.java))
        }
        binding.backButton3.setOnClickListener {
            var intent = Intent(this, FromHereToGo::class.java);
            startActivity(intent)
            finish()
        }
        binding.recycle.scrollToPosition(Common.cartList.size-1)
    }
}