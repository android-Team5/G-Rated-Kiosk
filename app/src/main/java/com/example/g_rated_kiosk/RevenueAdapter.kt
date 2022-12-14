package com.example.g_rated_kiosk

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.example.g_rated_kiosk.DataManage.MenuStock
import com.example.g_rated_kiosk.DataManage.MenuStocks
import com.example.g_rated_kiosk.DataManage.StocksManager
import com.example.g_rated_kiosk.databinding.AdminitemBinding
import com.example.g_rated_kiosk.databinding.SalesitemBinding
import kotlinx.android.synthetic.main.dialog_addstock.view.*

class DailyRevenueViewHolder (val binding: SalesitemBinding): RecyclerView.ViewHolder(binding.root)

class DailyRevenueAdapter (val salesList: MutableList<StocksManager.SalesData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
/* inflate(int resource, ViewGroup parent, boolean attachToRoot)
* - attachToRoot= false: parent is only used to create the correct subclass of LayoutParams
*/
        return DailyRevenueViewHolder(
            SalesitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false))
    }
    // onCreateViewHolder()에서 반환한 뷰 홀더 객체는 자동으로 onBindViewHolder()의 매개변수로 전달
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemBinding = (holder as DailyRevenueViewHolder).binding
        val sale = salesList[position]
        itemBinding.stockName.text = sale.name
        itemBinding.stockCount.text = sale.quantity.toString()
        itemBinding.revenue.text = (sale.quantity * sale.price).toString()

    }
    override fun getItemCount(): Int {
        return salesList.size
    }
}

class MonthlyRevenueViewHolder (val binding: SalesitemBinding): RecyclerView.ViewHolder(binding.root)

class MonthlyRevenueAdapter (val salesList: MutableList<StocksManager.SalesData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
/* inflate(int resource, ViewGroup parent, boolean attachToRoot)
* - attachToRoot= false: parent is only used to create the correct subclass of LayoutParams
*/
        return DailyRevenueViewHolder(
            SalesitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false))
    }
    // onCreateViewHolder()에서 반환한 뷰 홀더 객체는 자동으로 onBindViewHolder()의 매개변수로 전달
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemBinding = (holder as DailyRevenueViewHolder).binding
        val sale = salesList[position]
        itemBinding.stockName.text = sale.name
        itemBinding.stockCount.text = sale.quantity.toString()
        itemBinding.revenue.text = (sale.quantity * sale.price).toString()

    }
    override fun getItemCount(): Int {
        return salesList.size
    }
}