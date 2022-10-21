package com.heechan.iampig.data

import com.squareup.moshi.Json

data class Food(
    @Json(name="PRDLST_REPORT_NO")
    val foodId: String,
    @Json(name="BAR_CD")
    val barCodeId: String,
    @Json(name="BSSH_NM")
    val manufacturer: String,
    @Json(name = "PRDLST_DCNM")
    val foodKind: String,
    @Json(name = "PRDLST_NM")
    val foodName: String,
)