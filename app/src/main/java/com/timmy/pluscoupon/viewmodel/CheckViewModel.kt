package com.timmy.pluscoupon.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel


class CheckViewModel @ViewModelInject constructor(private val checkRepository: CheckRepository) : ViewModel() {

    fun getData() {
        checkRepository.init()
        checkRepository.getDataFromAPI()
    }

    fun getLiveDataInRealm() = checkRepository.getLiveDataInRealm()

}