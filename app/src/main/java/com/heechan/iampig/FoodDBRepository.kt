package com.heechan.iampig

import android.app.Application
import com.heechan.iampig.data.Food
import com.heechan.iampig.utils.Result
import java.time.LocalDateTime

class FoodDBRepository(application: Application) {
    val foodDao : FoodDao by lazy {
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