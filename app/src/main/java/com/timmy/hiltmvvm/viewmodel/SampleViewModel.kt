package com.timmy.hiltmvvm.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.timmy.hiltmvvm.viewmodel.SampleRepository


class SampleViewModel @ViewModelInject constructor(private val sampleRepository: SampleRepository) : ViewModel() {

    fun getData() {
        sampleRepository.init()
        sampleRepository.getDataFromAPI()
    }

    fun getLiveDataInRealm() = sampleRepository.getLiveDataInRealm()

}