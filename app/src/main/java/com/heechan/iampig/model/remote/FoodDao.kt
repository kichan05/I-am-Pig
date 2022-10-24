package com.heechan.iampig.model.remote

import androidx.room.*
import com.heechan.iampig.model.data.Food

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_table ORDER BY date DESC")
    fun getFoodData() : List<Food>

    @Query("SELECT * FROM food_table WHERE date = :date")
    fun getFoodDay(date : String) : List<Food>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(food : Food)

    @Delete
    suspend fun deleteFood(food: Food)
}