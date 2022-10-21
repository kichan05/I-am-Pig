package com.heechan.iampig

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.iampig.data.Food
import com.heechan.iampig.utils.State
import kotlinx.coroutines.*

class FoodScanViewModel : ViewModel() {
    val repository = FoodApiRepository()

    val barCodeId = MutableLiveData<String>()
    val foodData = MutableLiveData<Food>()
    val state = MutableLiveData<State>()

    fun getFoodDataByBarcodeId() {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            state.value = State.FAIL

            if(e is com.squareup.moshi.JsonDataException) {
                Log.d("foodData", "알수없는 데이터 입니다.")
            }
        }) {
            Log.d("foodApi", "가져오기 실행")
            state.value = State.LOADING
            val res = withContext(Dispatchers.IO) {
                repository.getFoodData(barcodeID = barCodeId.value!!)
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
}