package com.mayurjajoo.restaurant.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.mayurjajoo.restaurant.model.Menu
import com.mayurjajoo.restaurant.model.MenuX

class MenuRepository(private val mApplicationContext: Context) {

    private val _menuList = MutableLiveData<List<MenuX>>()

    val restaurantList: LiveData<List<MenuX>>
        get() = _menuList


    /**
     * Get Menu List
     */
    fun getMenuList() {
        val inputStream = mApplicationContext.assets.open("menu.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        val menu: Menu = gson.fromJson(json, Menu::class.java)
        Log.d("Repo", menu.toString())
        _menuList.postValue(menu.menus)
    }
}