package com.heechan.iampig

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodScanViewModel : ViewModel() {
    val barCodeId = MutableLiveData<String>()
}