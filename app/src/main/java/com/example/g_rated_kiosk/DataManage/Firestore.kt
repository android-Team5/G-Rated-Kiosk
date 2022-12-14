package com.example.g_rated_kiosk

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DBManager {

    companion object {

        val database = Firebase.firestore
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")


        fun addIncomingStock(productName: String, quantity:Int, price: Int){
            val currentDate = LocalDateTime.now().format(dateFormatter)
            val currentTime = LocalDateTime.now().format(timeFormatter)

            val incomingStockRef = database
                .collection("stock").document("incomingStock")
                .collection("$currentDate").document("$currentTime")

            val currentStockRef = database
                .collection("stock").document("currentStock")
                .collection("products").document("$productName")

            val stockData = hashMapOf(
                "productName" to "$productName",
                "price" to price,
                "quantity" to quantity

            )


            incomingStockRef.set(stockData)
            //update stock

            currentStockRef.get()
                .addOnSuccessListener { document ->
                    if(document.exists() && document != null){
                        val currentStock = document.data?.get("stock")

                        if(currentStock != null){
                            val newValue = currentStock.toString().toLong() + quantity

                            val t = currentStockRef.update("stock", newValue)
                            Log.d("$productName(currentStock)", "current stock updated to: $currentStock")

                        }
                    }
                    else{
                        Log.d("$productName(currentStock)", "document doesn't exist or document is null")
                    }
                }
                .addOnFailureListener {exception ->
                    Log.d("$productName(currentStock)", "get failed with", exception)
                }
        }

        fun addCurrentStock(productName: String, quantity:Int, price: Int){

            val currentStockRef = database
                .collection("stock").document("currentStock")
                .collection("products").document("$productName")

            val stockData = hashMapOf(
                "price" to "$price",
                "stock" to quantity
            )
            currentStockRef.set(stockData)

        }

        fun updateSales(productName: String, quantity:Int, price: Int, suffix:Int){
            val currentDate = LocalDateTime.now().format(dateFormatter)
            val currentTime = LocalDateTime.now().format(timeFormatter)

            val soldProductRef = database
                .collection("stock").document("sales")
                .collection("$currentDate").document("$currentTime:$suffix")

            val currentStockRef = database
                .collection("stock").document("currentStock")
                .collection("products").document("$productName")


            val salesData = hashMapOf(
                "productName" to "$productName",
                "price" to price,
                "quantitySold" to quantity

            )



            //receipt
            soldProductRef.set(salesData)


            //update stock
            currentStockRef.get()
                .addOnSuccessListener { document ->
                    if(document.exists() && document != null){
                        val currentStock = document.data?.get("stock")

                        if(currentStock != null && currentStock.toString().toInt() >= quantity){
                            val newValue = currentStock.toString().toLong() - quantity

                            currentStockRef.update("stock", newValue)
                            Log.d("$productName(currentStock)", "current stock updated to: $currentStock")

                        }
                    }
                    else{
                        Log.d("$productName(currentStock)", "document doesn't exist or document is null")
                    }
                }
                .addOnFailureListener {exception ->
                    Log.d("$productName(currentStock)", "get failed with", exception)
                }



        }
    }
}