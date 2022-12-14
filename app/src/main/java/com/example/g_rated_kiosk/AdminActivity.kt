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
    lateinit var binding:ActivityAdminBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var firstFragment= StockListFragment()
        val fragmentManager= supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(binding.adminFragment.id,firstFragment).commit()

    }
}