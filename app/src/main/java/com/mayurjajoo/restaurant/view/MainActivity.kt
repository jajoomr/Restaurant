package com.mayurjajoo.restaurant.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mayurjajoo.restaurant.R
import com.mayurjajoo.restaurant.RestaurantApplication
import com.mayurjajoo.restaurant.adapter.RestaurantListAdapter
import com.mayurjajoo.restaurant.viewModel.MainViewModel
import com.mayurjajoo.restaurant.viewModel.MainViewModelFactory

/**
 * Responsible for displaying Restaurant List
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mMainViewModel: MainViewModel
    private lateinit var mRestaurantAdapter: RestaurantListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    /**
     * Initialize components
     */
    private fun initialize() {
        mMainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                RestaurantApplication.restaurantRepository,
                RestaurantApplication.menuRepository
            )
        ).get(MainViewModel::class.java)
        //initialisation for recycler view
        mRecyclerView = findViewById(R.id.rv_restaurants)
        mRestaurantAdapter = RestaurantListAdapter(this)
        mRecyclerView.apply {
            adapter = mRestaurantAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        observeLiveDataAndUpdate()
    }

    /**
     * Observes live data from view model and updates UI
     */
    private fun observeLiveDataAndUpdate() {
        mMainViewModel.restaurantListLiveData.observe(this) {
            mRestaurantAdapter.updateList(it)
        }
    }
}