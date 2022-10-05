package com.mayurjajoo.restaurant.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.mayurjajoo.restaurant.model.Restaurant
import com.mayurjajoo.restaurant.model.Restaurants

class RestaurantRepository(private val context: Context) {


    private val _restaurantList = MutableLiveData<List<Restaurant>>()

    private var restaurants: Restaurants? = null


    val restaurantList: LiveData<List<Restaurant>>
        get() = _restaurantList


    /**
     * Get Restaurant Data List
     */
    fun getRestaurantList() {
        val inputStream = context.assets.open("restaurants.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        val restaurantList : Restaurants = gson.fromJson(json, Restaurants::class.java)
        Log.d("Repo", restaurantList.toString())
        _restaurantList.postValue(restaurantList.restaurants)
    }

}