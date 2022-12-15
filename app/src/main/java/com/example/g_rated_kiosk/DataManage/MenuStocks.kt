package com.example.g_rated_kiosk.DataManage

import com.example.g_rated_kiosk.MenuController
import com.example.g_rated_kiosk.MenuType
import com.example.g_rated_kiosk.MenuView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONArray

class MenuStocks {
    companion object{
        val notSoldout = mutableListOf<MenuStock>()
        val soldout = mutableListOf<MenuStock>()
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
                if(newStock.GetStock()<=MenuView.quantityThreshold)
                    soldout.add(newStock)
                else
                    notSoldout.add(newStock)
            }
            else{
                stock.Price = newStock.Price
                setStockOf(stock,newStock.GetStock())
            }
        }

        fun getStockOf(name:String):Int{
            val st = find(name)

            return st?.GetStock()?:-1
        }

        fun setStockOf(stock:MenuStock,value:Int){
            if(stock.GetStock()>MenuView.quantityThreshold){
                if(value< MenuView.quantityThreshold) {
                    notSoldout.remove(stock)
                    soldout.add(stock)
                }
            }
            else{
                if(value>MenuView.quantityThreshold){
                    soldout.remove(stock)
                    notSoldout.add(stock)
                }
            }
            stock.SetStock(value)
        }

        fun setStockOf(name:String,value:Int){
            val st = find(name)
            if(st==null)
                return
            if(st.GetStock()>MenuView.quantityThreshold){
                if(value<MenuView.quantityThreshold) {
                    notSoldout.remove(st)
                    soldout.add(st)
                }
            }
            else{
                if(value>MenuView.quantityThreshold){
                    soldout.remove(st)
                    notSoldout.add(st)
                }
            }
            st.SetStock(value)
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