package com.mayurjajoo.restaurant.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mayurjajoo.restaurant.R
import com.mayurjajoo.restaurant.RestaurantApplication
import com.mayurjajoo.restaurant.adapter.RestaurantListAdapter
import com.mayurjajoo.restaurant.viewModel.RestaurantListViewModel
import com.mayurjajoo.restaurant.viewModel.RestaurantListViewModelFactory

/**
 * Responsible for displaying Restaurant List
 */
class RestaurantListActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRestaurantListViewModel: RestaurantListViewModel
    private lateinit var mRestaurantAdapter: RestaurantListAdapter
    private lateinit var mSearchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    /**
     * Initialize components
     */
    private fun initialize() {
        mRestaurantListViewModel = ViewModelProvider(
            this,
            RestaurantListViewModelFactory(
                RestaurantApplication.restaurantRepository,
                RestaurantApplication.menuRepository
            )
        ).get(RestaurantListViewModel::class.java)

        //initialisation for recycler view
        mRecyclerView = findViewById(R.id.rv_restaurants)
        mRestaurantAdapter = RestaurantListAdapter(this)
        mRecyclerView.apply {
            adapter = mRestaurantAdapter
            layoutManager = LinearLayoutManager(this@RestaurantListActivity)
        }

        initializeSearchView()
    }

    /**
     * Initialise search view
     */
    private fun initializeSearchView() {
        mSearchView = findViewById(R.id.search_bar)
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchText: String): Boolean {
                filterList(searchText)
                return false
            }
        })
    }

    /**
     * filter list based on searched text
     *
     * @param searchText - text searched on search bar
     */
    private fun filterList(searchText: String) {
        mRestaurantListViewModel.filterList(searchText)
    }

    override fun onResume() {
        super.onResume()
        observeLiveDataAndUpdate()
        observeFilteredList()
    }

    private fun observeFilteredList() {
        mRestaurantListViewModel.filteredRestaurantListLiveData.observe(this) {
            mRestaurantAdapter.updateList(it)
        }
    }

    /**
     * Observes live data from view model and updates UI
     */
    private fun observeLiveDataAndUpdate() {
        mRestaurantListViewModel.restaurantListLiveData.observe(this) {
            mRestaurantAdapter.updateList(it)
        }
    }
}