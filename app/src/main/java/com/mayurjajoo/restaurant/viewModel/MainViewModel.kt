package com.mayurjajoo.restaurant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mayurjajoo.restaurant.model.MenuX
import com.mayurjajoo.restaurant.model.Restaurant
import com.mayurjajoo.restaurant.repository.MenuRepository
import com.mayurjajoo.restaurant.repository.RestaurantRepository

class MainViewModel(
    private val mRestaurantRepository: RestaurantRepository,
    private val mMenuRepository: MenuRepository
) : ViewModel() {

    val restaurantListLiveData: LiveData<List<Restaurant>>
        get() = mRestaurantRepository.restaurantList

    val menuListLiveData: LiveData<List<MenuX>>
        get() = mMenuRepository.restaurantList

    init {
        mRestaurantRepository.getRestaurantList()
        mMenuRepository.getMenuList()
    }
}