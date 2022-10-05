package com.mayurjajoo.restaurant.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mayurjajoo.restaurant.repository.MenuRepository
import com.mayurjajoo.restaurant.repository.RestaurantRepository

class MainViewModelFactory(
    private val mRestaurantRepository: RestaurantRepository,
    private val mMenuRepository: MenuRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mRestaurantRepository, mMenuRepository) as T
    }
}