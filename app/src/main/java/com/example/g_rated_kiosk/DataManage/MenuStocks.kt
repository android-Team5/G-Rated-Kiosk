package com.example.g_rated_kiosk.DataManage

import org.json.JSONArray

class MenuStocks {
    companion object{
        val stockList = mutableListOf<MenuStock>()

        fun toJson():JSONArray{
            val jArray = JSONArray()
            for (t in stockList){
                jArray.put(t.toJson())
            }

            return jArray
        }

        fun getFromJson(json:JSONArray){
            for(t in 0 until json.length()){
                stockList.add(MenuStock(json.getJSONObject(t)))
            }
        }

        fun find(name:String):MenuStock?{
            for(t in stockList){
                if (t.Name == name)
                    return t
            }
            return null
        }
    }
}