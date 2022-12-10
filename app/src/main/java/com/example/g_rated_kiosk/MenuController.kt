package com.example.g_rated_kiosk

import android.view.View

class MenuController {
    companion object{
        var isLoaded = false

        private val BurgerMenus = mutableListOf<Menu>()
        private val SideMenus = mutableListOf<Menu>()
        private val DrinkMenus = mutableListOf<Menu>()

        fun AddMenu(m:Menu){
            when(m.Type){
                MenuType.BURGER->
                    BurgerMenus.add(m)
                MenuType.SIDE->
                    SideMenus.add(m)
                MenuType.DRINKS->
                    DrinkMenus.add(m)
            }
        }

        fun GetMenus(type:MenuType):List<Menu>{
            return when(type){
                MenuType.BURGER->
                    BurgerMenus.toList()
                MenuType.DRINKS->
                    DrinkMenus.toList()
                MenuType.SIDE->
                    SideMenus.toList()
            }
        }

        fun FindMenu(menu: List<Menu>, name:String):Menu?{
            for(l in menu){
                if(l.Name == name)
                    return l
            }
            return null
        }

        fun InitiateMenus(){
            if(isLoaded)
                return

        }

    }
}