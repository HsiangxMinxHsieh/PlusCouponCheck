package com.timmy.pluscoupon.viewmodel

import com.timmy.pluscoupon.R
import com.timmy.pluscoupon.database.CheckData
import com.timmy.pluscoupon.di.CheckDataDao
import javax.inject.Inject

class GetRawRepository @Inject constructor() {

    @Inject
    lateinit var read: CheckDataDao

    fun getData():CheckData =read.getRawJson(R.raw.raw)

}
