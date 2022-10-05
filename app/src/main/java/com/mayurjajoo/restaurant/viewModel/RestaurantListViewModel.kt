package com.mayurjajoo.restaurant.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mayurjajoo.restaurant.model.MenuX
import com.mayurjajoo.restaurant.model.Restaurant
import com.mayurjajoo.restaurant.repository.MenuRepository
import com.mayurjajoo.restaurant.repository.RestaurantRepository

/**
 * Responsible for getting data from repository and updating View class
 */
class RestaurantListViewModel(
    private val mRestaurantRepository: RestaurantRepository,
    private val mMenuRepository: MenuRepository
) : ViewModel() {

    val restaurantListLiveData: LiveData<List<Restaurant>>
        get() = mRestaurantRepository.restaurantList

    private val menuListLiveData: LiveData<List<MenuX>>
        get() = mMenuRepository.restaurantList

    private val _filteredRestaurantList = MutableLiveData<List<Restaurant>>()

    val filteredRestaurantListLiveData: LiveData<List<Restaurant>>
        get() = _filteredRestaurantList

    init {
        mRestaurantRepository.getRestaurantList()
        mMenuRepository.getMenuList()
    }

    /**
     * filter list based on searched text
     *
     * @param searchText - text searched on search bar
     */
    fun filterList(searchText: String) {
        val filteredList: MutableList<Restaurant> = ArrayList()
        val restaurantIdList: MutableList<Int> = ArrayList()

        //search by menu name
        for (menu in menuListLiveData.value!!) {
            for (category in menu.categories) {
                for (menuItem in category.menuItems) {
                    if (menuItem.name.contains(searchText, true)) {
                        restaurantIdList.add(menu.restaurantId)
                    }
                }
            }
        }

        if (restaurantIdList.isNotEmpty()) {
            //if restaurant is found for searched menu item then update the filtered list
            for (restaurant in restaurantListLiveData.value!!) {
                if (restaurantIdList.contains(restaurant.id)) {
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

    private fun getFilteredListByMenu(searchText: String): MutableList<Int> {
        val restaurantIdList: MutableList<Int> = ArrayList()
        for (menu in menuListLiveData.value!!) {
            for (category in menu.categories) {
                for (menuItem in category.menuItems) {
                    if (menuItem.name.contains(searchText, true)) {
                        restaurantIdList.add(menu.restaurantId)
                    }
                }
            }
        }
        return restaurantIdList
    }
}