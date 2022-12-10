package com.example.g_rated_kiosk

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val database = Firebase.firestore
val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")


fun addIncomingStock(productName: String, quantity:Int, price: Int){
    val currentDate = LocalDateTime.now().format(dateFormatter)
    val currentTime = LocalDateTime.now().format(timeFormatter)

    val incomingStockRef = database
        .collection("stock").document("incomingStock")
        .collection("$currentDate").document("$currentTime")

    val stockData = hashMapOf(
        "productName" to "$productName",
        "price" to "$price",
        "quantity" to "$quantity"

    )


    incomingStockRef.set(stockData)

}

fun addCurrentStock(productName: String, quantity:Int, price: Int){

    val currentStockRef = database
        .collection("stock").document("currentStock")
        .collection("products").document("$productName")

    val stockData = hashMapOf(
        "price" to "$price",
        "stock" to "$quantity"

    )
    currentStockRef.set(stockData)

}

fun updateSales(productName: String, quantity:Int, price: Int){
    val currentDate = LocalDateTime.now().format(dateFormatter)
    val currentTime = LocalDateTime.now().format(timeFormatter)

    val soldProductRef = database
        .collection("stock").document("sales")
        .collection("$currentDate").document("$currentTime")


    val salesData = hashMapOf(
        "productName" to "$productName",
        "price" to "$price",
        "quantitySold" to "$quantity"

    )


    soldProductRef.set(salesData)

}