package com.mayurjajoo.restaurant.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.mayurjajoo.restaurant.model.Restaurant
import com.mayurjajoo.restaurant.model.Restaurants
import com.mayurjajoo.restaurant.utils.Constants

/**
 * Responsible to fetch restaurant data from data source and update view model
 */
class RestaurantRepository(private val mContext: Context) {

    private val _restaurantListLiveData = MutableLiveData<List<Restaurant>>()

    val restaurantListLiveData: LiveData<List<Restaurant>>
        get() = _restaurantListLiveData

    private val tag:String = RestaurantRepository::class.java.simpleName

    /**
     * Get Restaurant Data List
     */
    fun getRestaurantList() {
        val inputStream = mContext.assets.open(Constants.RESTAURANT_FILE_NAME)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        val restaurantList: Restaurants = gson.fromJson(json, Restaurants::class.java)
        Log.d(tag, restaurantList.toString())
        _restaurantListLiveData.postValue(restaurantList.restaurants)
    }

}