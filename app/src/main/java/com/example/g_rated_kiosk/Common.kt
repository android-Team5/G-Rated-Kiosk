package com.example.g_rated_kiosk

import android.app.Application


class cart {
    //var isBurger:Boolean = true;
    var menu:Menu? = null;
    var side:Menu? = null;
    var drink:Menu? = null;
    var noPickle= false;
    var noOnion = false;
    var count=0;
}
public class Common{
    companion object{
        fun initiateCartList(){
            val newCart = cart()
            newCart.count =1
            newCart.noOnion=false
            newCart.noPickle=true

            newCart.menu=MenuController.FindMenu(MenuController.GetMenus(MenuType.BURGER),"싸이버거")
            cartList.add(newCart)

        }

        fun clearChosen(){
            chosenMenu.menu= null;

            chosenMenu.side= null;
            chosenMenu.drink=null;
            chosenMenu.noOnion=false
            chosenMenu.noPickle=false
            chosenMenu.count=0
        }
        var cartList = mutableListOf<cart>()
        lateinit var chosenMenu:cart

        fun addToCart(){
            for (cart:cart in cartList) {
                if(cart.menu?.Name== chosenMenu.menu?.Name
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