package com.example.g_rated_kiosk

class Sales {

    var productName  = ""
    var price = 0
    var quantitySold = 0

    constructor(productName: String, price: Int, quantitySold: Int) {
        this.productName = productName
        this.price = price
        this.quantitySold = quantitySold
    }
}