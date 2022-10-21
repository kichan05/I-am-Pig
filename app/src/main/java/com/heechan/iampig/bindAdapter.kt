package com.heechan.iampig

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("bindVisibility")
fun bindVisibility(view : View, visibility : Boolean) {
    view.visibility = if(visibility) View.VISIBLE else View.INVISIBLE
}