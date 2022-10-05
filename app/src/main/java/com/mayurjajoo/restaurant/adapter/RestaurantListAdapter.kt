package com.mayurjajoo.restaurant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mayurjajoo.restaurant.R
import com.mayurjajoo.restaurant.model.Restaurant

/**
 * Adapter responsible for binding data to views
 */
class RestaurantListAdapter(private val mContext: Context) :
    RecyclerView.Adapter<RestaurantListAdapter.RestaurantListviewHolder>() {

    private var mRestaurantList: List<Restaurant> = ArrayList()

    inner class RestaurantListviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantName: TextView = itemView.findViewById(R.id.tv_restaurant_name)
        val cuisineType: TextView = itemView.findViewById(R.id.tv_cusine_type_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantListviewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.restaurant_item_layout, parent, false)
        return RestaurantListviewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantListviewHolder, position: Int) {
        val restaurantItem = mRestaurantList[position]
        holder.restaurantName.text = restaurantItem.name
        holder.cuisineType.text = restaurantItem.cuisine_type
    }

    /**
     * Updates List
     */
    fun updateList(restaurantList: List<Restaurant>) {
        mRestaurantList = restaurantList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mRestaurantList.size
    }
}