package com.heechan.iampig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.zxing.ResultPoint
import com.heechan.iampig.databinding.ActivityFoodScanBinding
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CaptureManager

class FoodScanActivity : AppCompatActivity() {
    private lateinit var capture: CaptureManager
    lateinit var binding : ActivityFoodScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_scan)

        capture = CaptureManager(this, binding.barCodeScannerFoodScan).apply {
            initializeFromIntent(this@FoodScanActivity.intent, savedInstanceState)
            decode()
        }

        binding.barCodeScannerFoodScan.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                binding.txtFoodScanBarcodeId.text = result.toString()
            }

            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {

            }
        })
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