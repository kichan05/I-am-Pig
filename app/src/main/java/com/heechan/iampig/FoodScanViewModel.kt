package com.heechan.iampig

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.iampig.data.Food
import kotlinx.coroutines.*

class FoodScanViewModel : ViewModel() {
    val repository = FoodApiRepository()

    val barCodeId = MutableLiveData<String>()
    val foodData = MutableLiveData<Food>()

    fun getFoodDataByBarcodeId() {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
//            Log.d("foodData", e.toString())

            if(e is com.squareup.moshi.JsonDataException) {
                Log.d("foodData", "알수없는 데이터 입니다.")
            }
        }) {
            val res = withContext(Dispatchers.IO) {
                repository.getFoodData(barcodeID = barCodeId.value!!)
            }

            if (res.isSuccessful && res.body()!!.C005.total_count >= 1) {
                val body = res.body()!!

                foodData.value = body.C005.row[0]
            } else {

            }
        }
    }
}