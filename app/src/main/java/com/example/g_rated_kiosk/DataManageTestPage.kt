package com.example.g_rated_kiosk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.g_rated_kiosk.DataManage.MenuStock
import com.example.g_rated_kiosk.DataManage.MenuStocks
import com.example.g_rated_kiosk.databinding.ActivityDataManageTestPageBinding

class DataManageTestPage : AppCompatActivity() {
    fun update(){
        var str:String = ""
        for(t in MenuStocks.stockList){
            str += "${t.Name}, ${t.Price}원, ${t.Stock}개 / ${t.Sales}\n"
        }
        binding.debug.text = str
    }

    fun saveJSON(){
    }

    fun loadJSON(){
        MenuStocks.stockList.clear()
        update()
    }

    lateinit var binding:ActivityDataManageTestPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataManageTestPageBinding.inflate(layoutInflater)

        binding.addStock.setOnClickListener {
            var t = MenuStocks.find(binding.testMenuName.text.toString())
            if(t == null){
                MenuStocks.stockList.add(MenuStock(binding.testMenuName.text.toString(),1234,1,0))
            }
            else{
                t.Stock += 1
            }

            update()
        }

        binding.removeStock.setOnClickListener {
            var t = MenuStocks.find(binding.testMenuName.text.toString())
            if(t!=null){
                t.Stock -= 1
                t.Sales += 1
            }

            update()
        }

        binding.loadJson.setOnClickListener { loadJSON() }
        binding.saveJson.setOnClickListener { saveJSON() }

        setContentView(binding.root)
    }
}