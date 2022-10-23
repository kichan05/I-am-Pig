package com.heechan.iampig.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.time.LocalDateTime

@Entity(tableName = "food_table")
data class Food(
    @Json(name="PRDLST_REPORT_NO")
    @PrimaryKey
    @ColumnInfo(name = "food_id")
    var foodId: String = "",

    @Json(name="BAR_CD")
    @ColumnInfo(name = "barcode_id")
    var barCodeId: String = "",

    @Json(name="BSSH_NM")
    @ColumnInfo(name = "manufacturer")
    var manufacturer: String = "",

    @Json(name = "PRDLST_DCNM")
    @ColumnInfo(name = "food_kind")
    var foodKind: String = "",

    @Json(name = "PRDLST_NM")
    @ColumnInfo(name = "food_name")
    var foodName: String = "",

    @Transient
    @ColumnInfo(name = "date")
    var date : String = LocalDateTime.now().toString()
)