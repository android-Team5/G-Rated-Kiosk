package com.example.g_rated_kiosk

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.g_rated_kiosk.DataManage.MenuStock
import com.example.g_rated_kiosk.DataManage.MenuStocks
import com.example.g_rated_kiosk.DataManage.StocksManager
import com.example.g_rated_kiosk.databinding.ActivityAdminBinding
import com.example.g_rated_kiosk.databinding.ActivityMainBinding
import com.example.g_rated_kiosk.databinding.AdminitemBinding
import com.example.g_rated_kiosk.databinding.ItemBinding
import kotlinx.android.synthetic.main.adminitem.view.*
import kotlinx.android.synthetic.main.dialog_addstock.view.*
import java.time.LocalDateTime

class MyAdminViewHolder (val binding: AdminitemBinding):RecyclerView.ViewHolder(binding.root)

class StockItemsAdapter (val stockList: MutableList<MenuStock>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
/* inflate(int resource, ViewGroup parent, boolean attachToRoot)
* - attachToRoot= false: parent is only used to create the correct subclass of LayoutParams
*/
        return MyAdminViewHolder(
            AdminitemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }
    // onCreateViewHolder()에서 반환한 뷰 홀더 객체는 자동으로 onBindViewHolder()의 매개변수로 전달
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        MenuStocks.notSoldout.sortBy{
            it.GetStock()
        }

        val itemBinding = (holder as MyAdminViewHolder).binding
        val stock = stockList[position]
        itemBinding.stockName.text = stock.Name
        itemBinding.stockCount.text = stock.GetStock().toString()

        val inflater = holder.itemView.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_addstock,null)
        val removalDialogView = inflater.inflate(R.layout.dialog_addstock,null)
        dialogView.addingStockName.text = itemBinding.stockName.text
        dialogView.labelText.text = "발주량 : "


        removalDialogView.addingStockName.text = itemBinding.stockName.text
        removalDialogView.labelText.text = "폐기량 : "
        for((i,t) in dialogView.increase.children.toList().withIndex()){
            when(i){
                0->
                (t as Button).setOnClickListener {
                    dialogView.addingStockQuantity.text = (dialogView.addingStockQuantity.text.toString().toInt() + 1).toString()
                }
                1->
                    (t as Button).setOnClickListener {
                        dialogView.addingStockQuantity.text = (dialogView.addingStockQuantity.text.toString().toInt() + 10).toString()
                    }
                2->
                    (t as Button).setOnClickListener {
                        dialogView.addingStockQuantity.text = (dialogView.addingStockQuantity.text.toString().toInt() + 100).toString()
                    }
            }
        }

        for((i,t) in dialogView.decrease.children.toList().withIndex()){
            when(i){
                0->
                    (t as Button).setOnClickListener {
                        dialogView.addingStockQuantity.text = (dialogView.addingStockQuantity.text.toString()
                            .toInt() - 1).coerceAtLeast(0).toString()
                    }
                1->
                    (t as Button).setOnClickListener {
                        dialogView.addingStockQuantity.text = (dialogView.addingStockQuantity.text.toString().toInt() - 10).coerceAtLeast(0).toString()
                    }
                2->
                    (t as Button).setOnClickListener {
                        dialogView.addingStockQuantity.text = (dialogView.addingStockQuantity.text.toString().toInt() - 100).coerceAtLeast(0).toString()
                    }
            }
        }

        for((i,t) in removalDialogView.increase.children.toList().withIndex()){
            when(i){
                0->
                    (t as Button).setOnClickListener {
                        removalDialogView.addingStockQuantity.text = (removalDialogView.addingStockQuantity.text.toString().toInt() + 1).coerceAtMost(t.rootView.findViewById<TextView>(R.id.currentStockQuantity).text.toString().toInt()).toString()
                    }
                1->
                    (t as Button).setOnClickListener {
                        removalDialogView.addingStockQuantity.text = (removalDialogView.addingStockQuantity.text.toString().toInt() + 10).coerceAtMost(t.rootView.findViewById<TextView>(R.id.currentStockQuantity).text.toString().toInt()).toString()
                    }
                2->
                    (t as Button).setOnClickListener {
                        removalDialogView.addingStockQuantity.text = (removalDialogView.addingStockQuantity.text.toString().toInt() + 100).coerceAtMost(t.rootView.findViewById<TextView>(R.id.currentStockQuantity).text.toString().toInt()).toString()
                    }
            }
        }

        for((i,t) in removalDialogView.decrease.children.toList().withIndex()){
            when(i){
                0->
                    (t as Button).setOnClickListener {
                        removalDialogView.addingStockQuantity.text = (removalDialogView.addingStockQuantity.text.toString().toInt() - 1).coerceAtLeast(0).toString()
                    }
                1->
                    (t as Button).setOnClickListener {
                        removalDialogView.addingStockQuantity.text = (removalDialogView.addingStockQuantity.text.toString().toInt() - 10).coerceAtLeast(0).toString()
                    }
                2->
                    (t as Button).setOnClickListener {
                        removalDialogView.addingStockQuantity.text = (removalDialogView.addingStockQuantity.text.toString().toInt() - 100).coerceAtLeast(0).toString()
                    }
            }
        }
        val dialog = AlertDialog.Builder(holder.itemView.context).apply {
            setTitle("발주")
            dialogView.currentStockQuantity.text= stock.GetStock().toString()
            setView(dialogView)
            setPositiveButton("발주") { _, _ ->
                if(dialogView.addingStockQuantity.text.toString().toInt()>0) {
                    MenuStocks.find(itemBinding.stockName.text.toString())?.let {
                        StocksManager.receptStock(
                            it,
                            dialogView.addingStockQuantity.text.toString().toInt()
                        )
                    }

                    MenuStocks.setStockOf(stock,stock.GetStock()+dialogView.addingStockQuantity.text.toString().toInt())
                    notifyDataSetChanged()
                }
            }
        }.create()

        val removalDialog = AlertDialog.Builder(holder.itemView.context).apply {
            setTitle("발주")
            removalDialogView.currentStockQuantity.text= stock.GetStock().toString()
            setView(removalDialogView)
            setPositiveButton("폐기") { _, _ ->
                if(removalDialogView.addingStockQuantity.text.toString().toInt()>0) {
                    MenuStocks.find(itemBinding.stockName.text.toString())?.let {
                        StocksManager.receptStock(
                            it,
                            -removalDialogView.addingStockQuantity.text.toString().toInt()
                        )
                    }

                    MenuStocks.setStockOf(stock,stock.GetStock()-removalDialogView.addingStockQuantity.text.toString().toInt())
                    notifyDataSetChanged()
                }
            }
        }.create()

        itemBinding.addStockButton.setOnClickListener {
            dialogView.addingStockQuantity.text = "0"
            dialog.show()
        }

        itemBinding.removeStockButton.setOnClickListener {
            removalDialogView.addingStockQuantity.text = "0"
            removalDialog.show()
        }


    }
    override fun getItemCount(): Int {
        return stockList.size
    }
}

class AdminActivity : AppCompatActivity() {
    companion object{
        var initedDaily = false
        var initedMonthly = false
        val dailySales = mutableListOf<StocksManager.SalesData>()
        val monthlySales = mutableListOf<StocksManager.SalesData>()

        fun findDaily(name:String):StocksManager.SalesData?{
            for(t in dailySales){
                if (t.name == name)
                    return t
            }
            return null
        }
        fun findMonthly(name:String):StocksManager.SalesData?{
            for(t in monthlySales){
                if (t.name == name)
                    return t
            }
            return null
        }

        fun updateToday(){

            val currentDate = LocalDateTime.now().format(DBManager.dateFormatter)
            dailySales.clear()

           DBManager.database
                .collection("stock").document("sales").collection("$currentDate").get().addOnSuccessListener {
                    for(t in it.documents){
                        val name = t["productName"].toString()
                        val k = findDaily(name)
                        if(k!=null){
                            k.quantity += t["quantitySold"].toString().toInt()
                        }
                        else{
                            dailySales.add(StocksManager.SalesData(t["productName"].toString(),
                            t["price"].toString().toInt(),t["quantitySold"].toString().toInt()))
                        }
                    }
                }
            initedDaily = true
        }

        fun addDay(datePrefix:String,dateSuffix:String){
            DBManager.database
                .collection("stock").document("sales").collection("$datePrefix-$dateSuffix").get().addOnSuccessListener {
                    var sum = 0
                    var totalQuantity = 0
                    for(t in it.documents){

                        Log.d("got data","${t["productName"]}, ${t["quantitySold"]}, ${t["price"]}")
                        sum += (t["quantitySold"]?.toString()?:"0").toInt() * (t["price"]?.toString()?:"0").toInt()
                        Log.d("got data",((t["quantitySold"]?.toString()?:"0").toInt() * (t["price"]?.toString()?:"0").toInt()).toString())
                        totalQuantity += (t["quantitySold"]?.toString()?:"0").toInt()
                    }
                    Log.d("got data","$dateSuffix, $sum, $totalQuantity")
                    monthlySales.add(StocksManager.SalesData(dateSuffix+"일",sum,totalQuantity))
                }
        }

        fun updateMonth(){
            monthlySales.clear()
            val db = DBManager.database
                .collection("stock").document("sales")
            val currentDate = LocalDateTime.now().format(DBManager.dateFormatter)
            val ss = currentDate.split('-')
            val prefix = ss[0]+'-'+ss[1]
            for(i in 1..ss[2].toInt()){
                addDay(datePrefix = prefix, dateSuffix = i.toString())
            }
            initedMonthly = true;
        }
    }
    lateinit var binding:ActivityAdminBinding

    val firstFragment= StockListFragment()
    val secondFragment= TodaysSalesFragment()
    val thirdFragment = MonthlySalesFragment()
    var fragment: Fragment = firstFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager= supportFragmentManager
        val _transaction = fragmentManager.beginTransaction()
        _transaction.replace(binding.adminFragment.id,firstFragment).commit()

        binding.stockStatusButton.setOnClickListener {
            if(fragment != firstFragment) {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(binding.adminFragment.id,firstFragment).commit()
                fragment = firstFragment
            }
        }
        binding.dailyButton.setOnClickListener {
            if(fragment != secondFragment) {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(binding.adminFragment.id,secondFragment).commit()
                fragment = secondFragment
            }
        }
        binding.monthlyButton.setOnClickListener {
            if(fragment != thirdFragment) {
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(binding.adminFragment.id,thirdFragment).commit()
                fragment = thirdFragment
            }
        }

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, FromHereToGo::class.java))
            finish()
        }

        updateMonth()
        updateToday()
    }
}