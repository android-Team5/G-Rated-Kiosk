package com.example.g_rated_kiosk.DataManage

class StockTransfer {
    val Name:String
    val Price:Int
    var Quantity:Int
    constructor(name:String, price:Int, quantity:Int){
        Name = name
        Price = price
        Quantity = quantity
    }
}