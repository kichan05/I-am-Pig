package com.heechan.iampig.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.iampig.databinding.RowFoodListBinding
import com.heechan.iampig.model.data.Food

class FoodListAdapter(val items : List<Food>) : RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder>() {
    class FoodListViewHolder(val binding : RowFoodListBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var foodData : Food

        fun onBind(foodData: Food) {
            this.foodData = foodData
            binding.foodData = foodData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowFoodListBinding.inflate(layoutInflater, parent, false)

        return FoodListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodListViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size
}