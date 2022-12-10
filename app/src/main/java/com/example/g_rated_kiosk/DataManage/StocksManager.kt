package com.example.g_rated_kiosk.DataManage

import com.example.g_rated_kiosk.Menu
import com.example.g_rated_kiosk.cart
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.TimeOfDay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class StocksManager {
    data class salesData(val price:Int, val quantitySold:Int, val time:String)
    companion object {

        lateinit var today:String

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

        fun makeToday(){
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            today = LocalDateTime.now().format(dateFormatter)
        }

        fun sellStock(soldMenu: Menu, quantity:Int){


            val item = Firebase.firestore.collection("stock").document("sales")
                .collection(today).add(salesData(soldMenu.Price,quantity,"test"))

        }

        fun receptStock(gotMenu: Menu, quantity:Int){

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