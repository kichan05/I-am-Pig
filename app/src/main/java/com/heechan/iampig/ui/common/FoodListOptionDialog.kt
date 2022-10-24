package com.heechan.iampig.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.heechan.iampig.databinding.DialogListOptionBinding
import com.heechan.iampig.model.data.Food
import com.heechan.iampig.model.remote.FoodDao
import com.heechan.iampig.model.remote.FoodDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.DomainLoadStoreParameter

class FoodListOptionDialog(val foodData: Food) : DialogFragment() {
    private lateinit var binding: DialogListOptionBinding

    private val roomDao: FoodDao by lazy {
        FoodDatabase.getDatabase(binding.root.context).foodDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogListOptionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            food = foodData

            btnFoodListOptionDelete.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    roomDao.deleteFood(foodData)

                    withContext(Dispatchers.Main) {
                        dismiss()
                    }
                }
            }
        }
    }
}