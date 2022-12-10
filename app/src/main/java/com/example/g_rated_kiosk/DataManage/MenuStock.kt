package com.example.g_rated_kiosk.DataManage

import org.json.JSONObject

class MenuStock {
    val Name:String
    val Price:Int
    var Stock:Int
    var Sales:Int


    constructor(name:String, price:Int, stock:Int, sales:Int){
        Name = name
        Price = price
        Stock = stock
        Sales = sales
    }

    constructor(json:JSONObject){
        Name = json.getString("name")
        Price = json.getInt("price")
        Stock = json.getInt("stock")
        Sales = json.getInt("sales")
    }

    fun toJson():JSONObject{
        val json = JSONObject()
        json.put("name",Name)
        json.put("price",Price)
        json.put("stock",Stock)
        json.put("sales",Sales)

        return json
    }
}