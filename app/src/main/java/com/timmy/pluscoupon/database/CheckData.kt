package com.timmy.pluscoupon.database
import com.google.gson.annotations.SerializedName


data class CheckData(
    @SerializedName("rawData")
    val rawData: List<RawData> = listOf()
)

data class RawData(
    @SerializedName("codes")
    val codes: List<String> = listOf(),
    @SerializedName("name")
    val name: String = ""
)