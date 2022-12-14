
package com.example.g_rated_kiosk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.g_rated_kiosk.databinding.ActivityMenuSelectBinding
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

        val itemBinding = (holder as MyViewHolder).binding

        itemBinding.itemMenuName.text= cartList[position].menu?.Name
        itemBinding.itemMenuCount.text=cartList[position].count.toString()
        if(cartList[position].side!=null){
            itemBinding.itemSideName.text= cartList[position].side?.Name
        }
        if(cartList[position].drink!=null){
            itemBinding.itemDrinkName.text= cartList[position].drink?.Name
        }
        itemBinding.itemRoot.setOnClickListener { // 뷰에 이벤트 추가
        }
      itemBinding.itemAdd.setOnClickListener { cartList[position].count +=1
          notifyDataSetChanged()

      }
        itemBinding.itemSub.setOnClickListener { if (cartList[position].count>1){
            cartList[position].count -=1

        }
            else {

                cartList.removeAt(position)
        }
            notifyDataSetChanged()
        }

    }
    override fun getItemCount(): Int {
        return cartList.size
    }
}