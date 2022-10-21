package com.heechan.iampig

import com.heechan.iampig.data.Food
import com.heechan.iampig.data.FoodApiResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FoodApiRepository {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    val clientBuilder = OkHttpClient.Builder().apply {
        addInterceptor(loggingInterceptor)
    }
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://openapi.foodsafetykorea.go.kr")
        .client(clientBuilder.build())
        .build()

    private val foodApiService = retrofit.create(FoodApiSservice::class.java)

    suspend fun getFoodData(barcodeID : String) : Response<FoodApiResponse> {
        val res = foodApiService.getFoodData(barcodeID = barcodeID)

        return res
    }
}