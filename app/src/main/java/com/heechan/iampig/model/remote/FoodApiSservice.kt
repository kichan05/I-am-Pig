package com.heechan.iampig.model.remote

import com.heechan.iampig.BuildConfig
import com.heechan.iampig.model.data.FoodApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodApiSservice {
    @GET("/api/${BuildConfig.API_KEY}/C005/json/1/2/BAR_CD={barCodeID}")
    suspend fun getFoodData(@Path("barCodeID") barcodeID : String) : Response<FoodApiResponse>
}