package com.example.g_rated_kiosk.DataManage

import android.util.Log
import com.example.g_rated_kiosk.Menu
import com.example.g_rated_kiosk.cart
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class StocksManager {
    data class StockData(val price:Int, val stock:Int)

    data class SalesData(val name:String, val price:Int, val quantity:Int)
    companion object {

        val timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
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
            Firebase.firestore.collection("stock").document("sales")
                .collection(today).document(LocalDateTime.now().format(dateFormatter)).set(SalesData(soldMenu.Name,soldMenu.Price,quantity))

            changeCurrentStock(soldMenu,-quantity)
        }

        fun changeCurrentStock(changingMenu:Menu, delta:Int){
            Firebase.firestore.collection("stock").document("currentStock").collection("product").document(changingMenu.Name).update("stock",MenuStocks.find(changingMenu.Name)!!.Stock+delta)
        }



        fun setStock(menu:Menu, quantity:Int){
            Firebase.firestore.collection("stock").document("currentStock")
                .collection("product").document(menu.Name).set(StockData(menu.Price,quantity))
        }

        fun updateStock(menuName:String, quantity: Int, price: Int){
            var m = MenuStocks.find(menuName)

            if(m==null){
                MenuStocks.stockList.add(MenuStock(menuName,price,quantity))
            }
            else{
                m.Stock = quantity
                m.Price = price
            }
        }

        fun getAllStocks():List<MenuStock>{
            val list = mutableListOf<MenuStock>()
            Firebase.firestore.collection("stock").document("currentStock").collection("product").get()
                .addOnSuccessListener { documents ->
                    for(s in documents){
                        list.add(MenuStock(s.id, s.data["price"] as Int, s.data["stock"] as Int))
                    }
                }

                .addOnFailureListener { exception ->
                    Log.e("Database Load Failed",exception.stackTraceToString())
                }

            return list
        }

        fun receptStock(gotMenu: Menu, quantity:Int){
            Firebase.firestore.collection("stock").document("incomingStock")
                .collection(today).document(LocalDateTime.now().format(dateFormatter)).set(SalesData(gotMenu.Name,gotMenu.Price,quantity))

            changeCurrentStock(gotMenu,+quantity)
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