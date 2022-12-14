package com.example.g_rated_kiosk.DataManage

import org.json.JSONObject

class MenuStock {
    val Name:String
    var Price:Int
    var Stock:Int


    constructor(name:String, price:Int, stock:Int){
        Name = name
        Price = price
        Stock = stock
    }


}