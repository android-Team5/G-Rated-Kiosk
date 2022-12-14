
package com.example.g_rated_kiosk

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.g_rated_kiosk.databinding.ActivityMenuSelectBinding
import com.example.g_rated_kiosk.databinding.ItemBinding
import com.example.g_rated_kiosk.databinding.PurchaseItemBinding

class MyPurchaseViewHolder (val binding: PurchaseItemBinding):RecyclerView.ViewHolder(binding.root)

class PurchaseListAdapter (val cartList: MutableList<cart>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
/* inflate(int resource, ViewGroup parent, boolean attachToRoot)
* - attachToRoot= false: parent is only used to create the correct subclass of LayoutParams
*/
        return MyPurchaseViewHolder(PurchaseItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }
    // onCreateViewHolder()에서 반환한 뷰 홀더 객체는 자동으로 onBindViewHolder()의 매개변수로 전달
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemBinding = (holder as MyPurchaseViewHolder).binding
        val item = cartList[position]
        itemBinding.itemImage.setImageDrawable(item.menu?.MenuImage);
        itemBinding.itemMenuName.text= item.menu?.Name
        itemBinding.itemMenuCount.text=item.count.toString()
        itemBinding.itemSideName.text= item.side?.Name?:""
        itemBinding.itemDrinkName.text= item.drink?.Name?:""


        itemBinding.purchasePrice.text = item.getPrice().toString() + "원"
        itemBinding.itemRoot.setOnClickListener { // 뷰에 이벤트 추가
        }

    }
    override fun getItemCount(): Int {
        return cartList.size
    }
}