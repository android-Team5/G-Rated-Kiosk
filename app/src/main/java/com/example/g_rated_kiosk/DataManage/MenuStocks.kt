package com.example.g_rated_kiosk.DataManage

import com.example.g_rated_kiosk.MenuController
import com.example.g_rated_kiosk.MenuType
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONArray

class MenuStocks {
    companion object{
        val stockList = mutableListOf<MenuStock>()



        fun find(name:String):MenuStock?{
            for(t in stockList){
                if (t.Name == name)
                    return t
            }
            return null
        }

        fun update(newStock:MenuStock){
            val stock = find(newStock.Name)
            if(stock == null){
                stockList.add(newStock)
            }
            else{
                stock.Price = newStock.Price
                stock.Stock = newStock.Stock
            }
        }


        fun updateStocks(){
            for (type in MenuType.values()){
                for(menu in MenuController.GetMenus(type)){
                    update(StocksManager.getTodayStock(menu))
                }
            }
        }
    }
}