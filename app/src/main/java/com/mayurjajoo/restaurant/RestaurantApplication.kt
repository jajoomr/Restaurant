package com.mayurjajoo.restaurant

import android.app.Application
import com.mayurjajoo.restaurant.repository.MenuRepository
import com.mayurjajoo.restaurant.repository.RestaurantRepository

/**
 * Restaurant application class responsible to initialise app level components
 */
class RestaurantApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        restaurantRepository = RestaurantRepository(applicationContext)
        menuRepository= MenuRepository(applicationContext)
    }

    companion object {
        lateinit var restaurantRepository: RestaurantRepository
        lateinit var menuRepository: MenuRepository
    }
}