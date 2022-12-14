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

                val index = Common.addToCart(cart(Common.chosenMenu))
                if(index==-1)
                    binding.recycle.adapter!!.notifyItemInserted(cartList.size-1)
                else
                    binding.recycle.adapter!!.notifyItemChanged(index)
                Common.chosenMenu = cart()
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
        UpdateAllMenus()

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