package com.mayurjajoo.restaurant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _filteredRestaurantList = MutableLiveData<List<Restaurant>>()

    val filteredRestaurantListLiveData: LiveData<List<Restaurant>>
        get() = _filteredRestaurantList

    init {
        mRestaurantRepository.getRestaurantList()
        mMenuRepository.getMenuList()
    }

    /**
     * Filters list based on search text
     */
    fun filterList(searchText: String) {
        val filteredList: MutableList<Restaurant> = ArrayList()
        val restIdList: MutableList<Int> = ArrayList()

        //search by menu name
        for (menu in menuListLiveData.value!!) {
            for (category in menu.categories) {
                for (menuItem in category.menuItems) {
                    if (menuItem.name.contains(searchText, true)) {
                        restIdList.add(menu.restaurantId)
                    }
                }
            }
        }

        if (restIdList.isNotEmpty()) {
            for (restaurant in restaurantListLiveData.value!!) {
                if (restIdList.contains(restaurant.id)) {
                    filteredList.add(restaurant)
                }
            }
        } else {
            //if search text is not found in menu items then
            //search by cuisine type
            for (restaurant in restaurantListLiveData.value!!) {
                if (restaurant.cuisine_type.contains(searchText, ignoreCase = true)) {
                    filteredList.add(restaurant)
                }
            }

            if (filteredList.isEmpty()) {
                //if search text is not found in cuisine type
                //search by restaurant name
                for (restaurant in restaurantListLiveData.value!!) {
                    if (restaurant.name.contains(searchText, ignoreCase = true)) {
                        filteredList.add(restaurant)
                    }
                }
            }
        }
        //finally update the live data
        _filteredRestaurantList.postValue(filteredList)
    }
}