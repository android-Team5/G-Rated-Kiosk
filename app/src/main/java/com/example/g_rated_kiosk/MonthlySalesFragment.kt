package com.example.g_rated_kiosk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.g_rated_kiosk.DataManage.MenuStock
import com.example.g_rated_kiosk.DataManage.MenuStocks
import com.example.g_rated_kiosk.databinding.FragmentStockListBinding

class MonthlySalesFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_monthly_sales, container, false)
        val left = view.findViewById<RecyclerView>(R.id.revenueRecycle)

        left.layoutManager = LinearLayoutManager(view.context)
        left.adapter = DailyRevenueAdapter(AdminActivity.monthlySales)

        AdminActivity.monthlySales.sortBy{
            it.name.replaceFirst("일","").toInt()
        }

        if(!AdminActivity.initedMonthly){
            AdminActivity.updateMonth()
        }
        var sum = 0

        for(t in AdminActivity.monthlySales){
            sum += t.price * t.quantity
        }

        view.findViewById<TextView>(R.id.totalRevenue).text = "당월 총 매출액 : " + sum.toString() + "원"

        return view
    }
}