package com.example.g_rated_kiosk.DataManage

import android.util.Log
import com.example.g_rated_kiosk.DBManager
import com.example.g_rated_kiosk.Menu
import com.example.g_rated_kiosk.cart
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Date

class StocksManager {
    data class StockData(val price:Int, val stock:Int)

    data class SalesData(val name:String, val price:Int, val quantity:Int)
    companion object {
         var today:String = ""

        fun sellStock(soldItems:List<cart>){
            for(i in soldItems){
                i.menu?.let{
                    sellStock(it,i.count)
                }

                i.side?.let {
                    sellStock(it,i.count)
                }

                i.drink?.let {
                    sellStock(it,i.count)
                }
            }
        }

        fun sellStock(soldMenu: Menu, quantity:Int){
            DBManager.updateSales(soldMenu.Name,quantity,soldMenu.Price)
        }

        fun receptStock(gotMenu: Menu, quantity:Int){
            DBManager.addIncomingStock(gotMenu.Name,quantity,gotMenu.Price)
        }

        fun initiateStocks():Task<QuerySnapshot>{
            Log.d("database Initiation","updating stocks...")

            DBManager.database.collection("stock").document("currentStock").collection("products").addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w("TAG", "Listen failed.", e)
                    return@addSnapshotListener
                }
                Log.d("database Initiation","updating stocks...")

                val stockChanges = mutableListOf<MenuStock>()

                for (doc in value!!) {
                    stockChanges.add(MenuStock(doc.id,(doc.data["price"] as Long).toInt(), (doc.data["stock"] as Long).toInt()))
                    Log.d("data From Database Update",doc.data.values.toString())
                }

                for(s in stockChanges){
                    MenuStocks.update(s)
                }
            }
            return getAllStocks()
        }

        fun getAllStocks(): Task<QuerySnapshot> {
            return Firebase.firestore.collection("stock").document("currentStock").collection("products").get()
        }


        fun getTodayStock(menu: Menu):MenuStock{
            if(true){
                return MenuStock(menu.Name,0,0)
            }
            else{
                return MenuStock(menu.Name,0,0)
            }
        }

        fun getSalesOfDay(menu: Menu,date:Date):StockTransfer{
            return StockTransfer(menu.Name,0,0)
        }

        fun getReceiptOfDay(menu: Menu,date:Date):StockTransfer{
            return StockTransfer(menu.Name,0,0)
        }
    }
}