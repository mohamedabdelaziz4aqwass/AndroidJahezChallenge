package com.mohamedabdelaziz.jahez.presentation.ui

import android.annotation.SuppressLint
import android.content.Context

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
 import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide

import androidx.databinding.DataBindingUtil
import com.mohamedabdelaziz.jahez.R
import com.mohamedabdelaziz.jahez.databinding.ItemRestaurantBinding
import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem
import java.util.ArrayList

class RestaurantAdapter(private val context: Context) : RecyclerView.Adapter<RestaurantAdapter.RestaurantHolder>() {
    private var restaurantsResponseList: MutableList<RestaurantsResponseItem> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantHolder {
        val itemRestaurantBinding: ItemRestaurantBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_restaurant, parent, false)
        return RestaurantHolder(itemRestaurantBinding)
    }

    override fun onBindViewHolder(holder: RestaurantHolder, position: Int) {
        val restaurantsResponseItem = restaurantsResponseList[position]
        //parent item
        holder.itemRestaurantBinding.nameTv.text = restaurantsResponseItem.name
        holder.itemRestaurantBinding.ratingTv.text = restaurantsResponseItem.rating.toString()
        if (restaurantsResponseItem.hasOffer)
        holder.itemRestaurantBinding.offerImg.visibility= View.VISIBLE
        else holder.itemRestaurantBinding.offerImg.visibility= View.GONE
        Glide.with(context).load(restaurantsResponseItem.image)
            .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.itemRestaurantBinding.restaurantImg)

    }

    override fun getItemCount(): Int {
        return restaurantsResponseList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(restaurantsResponseList: List<RestaurantsResponseItem>) {
         this.restaurantsResponseList = restaurantsResponseList as  MutableList<RestaurantsResponseItem>
        notifyDataSetChanged()
    }

    class RestaurantHolder(var itemRestaurantBinding: ItemRestaurantBinding) : RecyclerView.ViewHolder(itemRestaurantBinding.root)



}