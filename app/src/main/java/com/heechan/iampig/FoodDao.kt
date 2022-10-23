package com.heechan.iampig

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heechan.iampig.data.Food
import java.time.LocalDateTime

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_table")
    fun getFoodData() : List<Food>

    @Query("SELECT * FROM food_table WHERE date = :date")
    fun getFoodDay(date : String) : List<Food>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(food : Food)
}