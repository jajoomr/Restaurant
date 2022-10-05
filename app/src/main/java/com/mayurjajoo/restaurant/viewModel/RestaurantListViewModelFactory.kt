package com.mayurjajoo.restaurant.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mayurjajoo.restaurant.repository.MenuRepository
import com.mayurjajoo.restaurant.repository.RestaurantRepository

/**
 * Responsible for providing RestaurantViewModel
 */
class RestaurantListViewModelFactory(
    private val mRestaurantRepository: RestaurantRepository,
    private val mMenuRepository: MenuRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RestaurantListViewModel(mRestaurantRepository, mMenuRepository) as T
    }
}