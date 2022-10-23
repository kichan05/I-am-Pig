package com.heechan.iampig

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@BindingAdapter("bindVisibility")
fun bindVisibility(view : View, visibility : Boolean) {
    view.visibility = if(visibility) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("bindDateTimeFormat")
fun bindDateTimeFormat(textView: TextView, dateTime : String) {
    val date = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

    Log.d("bindDateTimeFormat", date.toString())

    textView.text = "${date.year}년 ${date.monthValue}월 ${date.dayOfMonth}일 ${date.hour}시"
}