
package com.example.g_rated_kiosk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.g_rated_kiosk.databinding.ItemBinding


class CartAdapter (val cartList: MutableList<cart>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
/* inflate(int resource, ViewGroup parent, boolean attachToRoot)
* - attachToRoot= false: parent is only used to create the correct subclass of LayoutParams
*/
        return MyViewHolder(ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }
    // onCreateViewHolder()에서 반환한 뷰 홀더 객체는 자동으로 onBindViewHolder()의 매개변수로 전달
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding

        binding.itemMenuName.text= cartList[position].menu?.Name + cartList[position].count.toString()

        binding.itemRoot.setOnClickListener { // 뷰에 이벤트 추가
        }
    }
    override fun getItemCount(): Int {
        return cartList.size
    }
}