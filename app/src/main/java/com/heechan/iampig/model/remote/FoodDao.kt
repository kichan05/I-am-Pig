package com.heechan.iampig.model.remote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heechan.iampig.model.data.Food

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_table")
    fun getFoodData() : List<Food>

    @Query("SELECT * FROM food_table WHERE date = :date")
    fun getFoodDay(date : String) : List<Food>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(food : Food)
}