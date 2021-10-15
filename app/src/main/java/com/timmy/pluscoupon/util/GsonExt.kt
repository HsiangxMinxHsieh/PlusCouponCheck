package com.timmy.pluscoupon.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import org.json.JSONObject
import timber.log.Timber

private val TAG = "GsonExt"
fun <T> String?.toDataBean(classOfT: Class<T>?): T? {
    return if (this.isJson()) Gson().fromJson(this, classOfT)
    else null
}

fun <T : Any> T.toJson(): String {
    return GsonBuilder().disableHtmlEscaping().create().toJson(this) ?: ""
}

fun String?.isJson(): Boolean {
    if (isNullOrEmpty()) {
        Timber.e( "response json is null or empty:: ${this.toString()}")
        return false
    }
    var jsonObject: JSONObject? = null
    try {
        jsonObject = JSONObject(this)
    } catch (e: Exception) {
         Timber.e(  "response json is:: $this \r\n ${e.message}")
         Timber.e(  "check fail, this string is not JSON format")
    }
    return jsonObject != null
}