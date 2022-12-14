package com.example.g_rated_kiosk

import android.app.Application
import com.example.g_rated_kiosk.DataManage.MenuStocks


class cart {
    //var isBurger:Boolean = true;
    var menu:Menu? = null;
    var side:Menu? = null;
    var drink:Menu? = null;
    var noPickle= false;
    var noOnion = false;
    var noLettuce= false;
    var count=0;

    constructor(){

    }

    constructor(clone:cart){
        menu = clone.menu
        side = clone.side
        drink = clone.drink
        noPickle= clone.noPickle
        noOnion = clone.noOnion
        noLettuce= clone.noLettuce
        count= clone.count;

    }

    fun getPrice():Int{
        var priceSum:Int = 0
        menu?.let{
            priceSum += MenuStocks.find(it.Name)?.Price?:0
        }
        side?.let{
            priceSum += MenuStocks.find(it.Name)?.Price?:0
        }
        drink?.let{
            priceSum += MenuStocks.find(it.Name)?.Price?:0
        }

        if(side!=null){
            priceSum -= 1300
        }
        priceSum *= count
        return priceSum
    }
}
public class Common{
    companion object{


//        fun clearChosen(){
//            chosenMenu.menu= null;
//
//            chosenMenu.side= null;
//            chosenMenu.drink=null;
//            chosenMenu.noOnion=false
//            chosenMenu.noPickle=false
//            chosenMenu.count=0
//        }
        var cartList = mutableListOf<cart>()
        var chosenMenu:cart = cart()
        fun addToCart(cm:cart):Int{
            var check = 0
            var index = 0
            for (cart:cart in cartList) {
                if(cart.menu!!.Name== cm.menu!!.Name
                    && cart.side == cm.side
                    && cart.drink == cm.drink
                    && cart.noOnion == cm.noOnion
                    && cart.noLettuce == cm.noLettuce
                    && cart.noPickle == cm.noPickle){
                    cart.count = (cart.count + 1).coerceAtMost(MenuView.quantityThreshold)
                    check=1
                    return index
                }
                index++
            }
            if(check==0) {
               cm.count=1
                cartList.add(cm)
            }
            chosenMenu = cart()
            return -1
        }

    }
}