package com.example.g_rated_kiosk.DataManage

import org.json.JSONObject

class MenuStock {
    val Name:String
    var Price:Int
    private var Stock:Int

    fun GetStock():Int{
        return Stock
    }

    fun SetStock(newStock:Int){
        Stock = newStock
    }

    constructor(name:String, price:Int, stock:Int){
        Name = name
        Price = price
        Stock = stock
    }


}