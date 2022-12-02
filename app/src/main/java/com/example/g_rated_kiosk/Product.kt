package com.example.g_rated_kiosk

class Product {
    var productName  = ""
    var price = 0
    var stock = 0

    //constructor()  // 파이어베이스에서 데이터 변환을 위해서 필요

    //for inventory
    constructor(productName: String, price: Int, stock: Int) {
        this.productName = productName
        this.price = price
        this.stock = stock
    }


}