package com.timmy.pluscoupon.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import org.json.JSONObject
import timber.log.Timber

private val TAG = "GsonExt"
fun <T> String?.toDataBean(classOfT: Class<T>?): T? {
    return Gson().fromJson(this, classOfT)
}

fun <T : Any> T.toJson(): String {
    return GsonBuilder().disableHtmlEscaping().create().toJson(this) ?: ""
}
