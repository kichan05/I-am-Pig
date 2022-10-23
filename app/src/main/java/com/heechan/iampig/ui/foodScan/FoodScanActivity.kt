package com.heechan.iampig.ui.foodScan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.ResultPoint
import com.heechan.iampig.R
import com.heechan.iampig.databinding.ActivityFoodScanBinding
import com.heechan.iampig.utils.State
import com.heechan.iampig.utils.State.*
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CaptureManager

class FoodScanActivity : AppCompatActivity() {
    lateinit var binding : ActivityFoodScanBinding
    lateinit var capture: CaptureManager
    val viewModel : FoodScanViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FoodScanViewModel(application) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityFoodScanBinding>(this,
            R.layout.activity_food_scan
        ).apply {
            lifecycleOwner = this@FoodScanActivity
            vm = viewModel
        }

        capture = CaptureManager(this, binding.barCodeScannerFoodScan).apply {
            initializeFromIntent(this@FoodScanActivity.intent, savedInstanceState)
            decode()
        }

        binding.barCodeScannerFoodScan.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                if(viewModel.barCodeId.value != result.toString()) {
                    viewModel.barCodeId.value = result.toString()
                }
            }

            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {

            }
        })

        binding.btnFoodScanRegisterFood.setOnClickListener {
            viewModel.insertFood()

        }

        viewModel.barCodeId.observe(this) {
            Log.d("foodApi", "변경됨")
            viewModel.getFoodDataByBarcodeId()
        }

        viewModel.roomState.observe(this) {
            when(it) {
                OK -> {
                    finish()
                }
                LOADING -> {}
                FAIL -> {
                    Toast.makeText(this, "음식 추가에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        capture.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        capture.onSaveInstanceState(outState)
    }

}