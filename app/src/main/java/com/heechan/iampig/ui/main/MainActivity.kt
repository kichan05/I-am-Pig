package com.heechan.iampig.ui.main
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.heechan.iampig.ui.foodScan.FoodScanActivity
import com.heechan.iampig.R
import com.heechan.iampig.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val viewModel : MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(application) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel

        binding.run {
            btnMainAddFood.setOnClickListener {
                val intent = Intent(this@MainActivity, FoodScanActivity::class.java)
                startActivityForResult(intent, SC_CODE)
            }
        }

        supportFragmentManager

        viewModel.getAllData()

        viewModel.foodList.observe(this) {
            val adapter = FoodListAdapter(it)
            binding.listMainFoodList.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            SC_CODE -> {
                viewModel.getAllData()
            }
        }
    }

    companion object {
        const val SC_CODE = 1
    }
}