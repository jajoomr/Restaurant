package com.mayurjajoo.restaurant.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.mayurjajoo.restaurant.model.Menu
import com.mayurjajoo.restaurant.model.MenuX
import com.mayurjajoo.restaurant.utils.Constants

/**
 * Responsible to fetch data of Menu from data source and update view model
 */
class MenuRepository(private val mApplicationContext: Context) {

    private val _menuListLiveData = MutableLiveData<List<MenuX>>()

    val menuListLiveData: LiveData<List<MenuX>>
        get() = _menuListLiveData

    private val tag: String = MenuRepository::class.java.simpleName

    /**
     * Get Menu List
     */
    fun getMenuList() {
        val inputStream = mApplicationContext.assets.open(Constants.MENU_FILE_NAME)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        val menu: Menu = gson.fromJson(json, Menu::class.java)
        Log.d(tag, menu.toString())
        _menuListLiveData.postValue(menu.menus)
    }
}