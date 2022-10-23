package com.heechan.iampig.model.remote

import android.app.Application
import com.heechan.iampig.model.data.Food
import java.time.LocalDateTime

class FoodDBRepository(application: Application) {
    private val foodDao : FoodDao by lazy {
        FoodDatabase.getDatabase(application).foodDao()
    }

    fun insertFood(foodData : Food) {
        foodDao.insert(foodData)
    }

    fun getAll() : List<Food> {
        return foodDao.getFoodData()
    }

    fun getAllDate(date : LocalDateTime = LocalDateTime.now()) : List<Food> {
        return foodDao.getFoodDay(date.toString())
    }
}