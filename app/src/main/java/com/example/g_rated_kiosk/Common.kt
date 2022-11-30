package com.example.g_rated_kiosk

import android.app.Application


class cart {
    var menuName="";
    var side="";
    var drink="";
    var noPickle= false;
    var noOnion = false;
    var count=0;
}
public class Common{
    companion object{
        fun clearChosen(){
            chosenMenu.menuName=""
            chosenMenu.side=""
            chosenMenu.drink=""
            chosenMenu.noOnion=false
            chosenMenu.noPickle=false
            chosenMenu.count=0
        }
        var cartList = mutableListOf<cart>()
        lateinit var chosenMenu:cart
        fun addToCart(){
            for (cart:cart in cartList) {
                if(cart.menuName== chosenMenu.menuName
                    && cart.side == chosenMenu.side
                    && cart.drink == chosenMenu.drink
                    && cart.noOnion == chosenMenu.noOnion
                    && cart.noPickle == chosenMenu.noPickle){
                    cart.count +=1
                }
                else {
                    break;
                }
            }
            var temp = chosenMenu
            temp.count=1
            cartList.add(temp)
            clearChosen()
        }

    }
}