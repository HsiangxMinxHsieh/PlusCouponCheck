package com.timmy.pluscoupon.viewmodel

import androidx.lifecycle.MutableLiveData
import com.timmy.pluscoupon.database.CheckData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CheckRepository @Inject constructor() {

    private val result by lazy { MutableLiveData<MutableList<CheckData>>() }

    fun getLiveDataInRealm() = result

    fun init(){ // 初始化，用於偵測資料庫是否有變化，或直接回傳值
    }

    fun getDataFromAPI() {
        CoroutineScope(Dispatchers.IO).launch {
//            val responseBody = apiService.getData()

            MainScope().launch {
            //處理畫面更新
//                responseBody.articles.forEach {
//                }

            }
        }
    }



}
