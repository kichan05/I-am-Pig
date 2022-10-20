package com.heechan.iampig
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.heechan.iampig.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.run {
            btnMainAddFood.setOnClickListener {
                val intent = Intent(this@MainActivity, FoodScanActivity::class.java)
                startActivityForResult(intent, SC_CODE)
            }
        }
    }

    companion object {
        const val SC_CODE = 1
    }
}