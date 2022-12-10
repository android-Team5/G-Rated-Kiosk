package com.example.g_rated_kiosk.DataManage

import com.example.g_rated_kiosk.Menu
import com.example.g_rated_kiosk.cart
import java.util.Date

class StocksManager {
    companion object {
        fun sellStock(soldItems:List<cart>){

        }

        fun sellStock(soldMenu: Menu, quantity:Int){

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