package com.example.g_rated_kiosk

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val database = Firebase.firestore


fun addIncomingStock(productName: String, quantity:Int, price: Int, date: String, time: String){

    val incomingStockRef = database
        .collection("stock").document("incomingStock")
        .collection("$date").document("$productName")

    val stockData = hashMapOf(
        "orderTime" to "$time",
        "price" to "$price",
        "quantity" to "$quantity"

    )


    incomingStockRef.set(stockData)

}

fun addCurrentStock(productName: String, quantity:Int, price: Int, date: String, time: String){

    val currentStockRef = database
        .collection("stock").document("currentStock")
        .collection("$date").document("$productName")

    val stockData = hashMapOf(
        "updateTime" to "$time",
        "price" to "$price",
        "stockQuantity" to "$quantity"

    )


    currentStockRef.set(stockData)

}

fun updateSales(productName: String, quantity:Int, price: Int, date: String, time: String){

    val soldProductRef = database
        .collection("stock").document("sales")
        .collection("$date").document("$productName")

    val salesData = hashMapOf(
        "time" to "$time",
        "price" to "$price",
        "quantitySold" to "$quantity"

    )


    soldProductRef.set(salesData)

}