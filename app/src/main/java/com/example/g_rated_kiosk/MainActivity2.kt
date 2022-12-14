package com.example.g_rated_kiosk

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.g_rated_kiosk.DataManage.MenuStock
import com.example.g_rated_kiosk.DataManage.MenuStocks
import com.example.g_rated_kiosk.DataManage.StocksManager
import com.example.g_rated_kiosk.databinding.FhtgBinding

class MainActivity2 : AppCompatActivity() {
    var eatWhere=""
    var logoClick=0
    @SuppressLint("UseCompatLoadingForDrawables")
    fun initMenu(){
        if(MenuController.isLoaded)
            return
        MenuController.AddMenu(Menu(MenuType.BURGER,"싸이버거",4300,getDrawable(R.drawable.psyburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"갈릭바베큐치킨버거",5600,getDrawable(R.drawable.garlicbarbequechickenburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"딥치즈싸이버거",4800,getDrawable(R.drawable.deeppsycheeseburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"불고기버거",3500,getDrawable(R.drawable.bulgogiburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"불싸이버거",4400,getDrawable(R.drawable.hotpsyburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"새우불고기버거",5100,getDrawable(R.drawable.shrimpbulgogiburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"쉬림프싸이플렉스버거",6500,getDrawable(R.drawable.shrimppsyflexburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"야채통통치킨버거",5900,getDrawable(R.drawable.vegichickenburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"어메이징매콤마요버거",5600,getDrawable(R.drawable.amazinghotmayoburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"오리지널미트볼버거",5900,getDrawable(R.drawable.originermeatballburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"인크레더블버거",7500,getDrawable(R.drawable.incredibleburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"청양마요미트볼버거",5900,getDrawable(R.drawable.chmayomeatballburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"트리플딥치즈싸이버거",5100,getDrawable(R.drawable.tripledeeppsyflexburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"화이트갈릭싸이버거",4900,getDrawable(R.drawable.whitegarlicpsyburger)))
        MenuController.AddMenu(Menu(MenuType.BURGER,"휠렛버거",4100,getDrawable(R.drawable.filletburger)))

        MenuController.AddMenu(Menu(MenuType.DRINKS,"콜라",1600,getDrawable(R.drawable.d_cola)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"레몬에이드",2200,getDrawable(R.drawable.d_lemonade)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"마운틴듀",1600,getDrawable(R.drawable.d_mountaindew)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"미란다",1600,getDrawable(R.drawable.d_miranda)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"사이다",1600,getDrawable(R.drawable.d_cider)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"아메리카노(HOT)",2000,getDrawable(R.drawable.d_americano_hot)))
        MenuController.AddMenu(Menu(MenuType.DRINKS,"아메리카노(ICE)",2000,getDrawable(R.drawable.d_americano_ice)))


        MenuController.AddMenu(Menu(MenuType.SIDE,"바삭크림치즈볼",2000,getDrawable(R.drawable.s_basakcheese)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"찐감자찰빵",3500,getDrawable(R.drawable.s_realpotatobread)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"찐고구마찰빵",3500,getDrawable(R.drawable.s_realsweetpotatobread)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"치즈스틱",2000,getDrawable(R.drawable.s_cheesestick)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"케이준양념감자",2000,getDrawable(R.drawable.s_kpotato)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"코울슬로",1800,getDrawable(R.drawable.s_kourslo)))
        MenuController.AddMenu(Menu(MenuType.SIDE,"콘샐러드",1800,getDrawable(R.drawable.s_cornsalad)))

        val list = mutableListOf<MenuStock>()
        StocksManager.initiateStocks().addOnSuccessListener { documents ->
            Log.d("database Initiation","database load success...")
            for(s in documents){
                list.add(MenuStock(s.id, (s.data["price"] as Long).toInt(), (s.data["stock"] as Long).toInt()))
            }
            for (t in list){
                Log.d("data From Database Update",String.format("name : ${t.Name}, stock : ${t.GetStock()}"))
                MenuStocks.update(t)
            }
        }
            .addOnFailureListener { exception ->
                Log.e("Database Load Failed",exception.stackTraceToString())
            }

        MenuController.isLoaded = true

        //StocksManager.setAllStocks()

    }
    override fun onCreate(savedInstanceState: Bundle?) {

        val fhtgBinding = FhtgBinding.inflate(layoutInflater)


        super.onCreate(savedInstanceState)
        setContentView(fhtgBinding.root)
        initMenu()



        fhtgBinding.fromHere.setOnClickListener {

            eatWhere = "fromHere"
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
        }
        fhtgBinding.toGo.setOnClickListener {
            eatWhere = "toGo"
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
        }
        fhtgBinding.toGo.setOnLongClickListener {


            if (logoClick==5){
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent);
            }

            return@setOnLongClickListener true;
        }
        fhtgBinding.logo.setOnClickListener {
            if(logoClick==5){
                logoClick=0
            }
            else{
                logoClick +=1
            }
        }


    }
}