package com.heechan.iampig.ui.main

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.iampig.model.data.Food
import com.heechan.iampig.model.remote.FoodDBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : ViewModel() {
    private val roomRepository = FoodDBRepository(application)

    val foodList = MutableLiveData<List<Food>>()

    fun getAllData() {
        viewModelScope.launch {
            val foodData = withContext(Dispatchers.IO){
                roomRepository.getAll()
            }
            foodList.value = foodData
        }
    }
}