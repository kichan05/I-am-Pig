package com.heechan.iampig

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.iampig.data.Food
import com.heechan.iampig.utils.Result
import com.heechan.iampig.utils.State
import kotlinx.coroutines.*

class FoodScanViewModel(application: Application) : ViewModel() {
    private val apiRepository = FoodApiRepository()
    private val roomRepository = FoodDBRepository(application)

    val barCodeId = MutableLiveData<String>()
    val foodData = MutableLiveData<Food>()
    val state = MutableLiveData<State>()

    fun getFoodDataByBarcodeId() {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            state.value = State.FAIL
            Log.e("foodApi", "API Error ${e.message}")

            if(e is com.squareup.moshi.JsonDataException) {
                Log.e("foodApi", "알수없는 데이터 입니다.")
            }
        }) {
            state.value = State.LOADING

            val res = withContext(Dispatchers.IO) {
                apiRepository.getFoodData(barcodeID = barCodeId.value!!)
            }

            if (res.isSuccessful && res.body()!!.C005.total_count >= 1) {
                val body = res.body()!!
                state.value = State.OK

                foodData.value = body.C005.row[0]
                Log.d("foodApi", foodData.value.toString())
            } else {
                state.value = State.FAIL
            }
        }
    }

    fun insertFood() {
        viewModelScope.launch {
            state.value = State.LOADING
            withContext(Dispatchers.IO) {
                roomRepository.insertFood(foodData.value!!)
            }
            state.value = State.OK

            Log.d("foodInsert", "음식 추가 완료")
        }
    }
}