package com.timmy.hiltmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.timmy.hiltmvvm.R
import com.timmy.hiltmvvm.databinding.ActivitySampleBinding
import com.timmy.hiltmvvm.viewmodel.SampleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleActivity : AppCompatActivity() {
    private val viewModel: SampleViewModel by lazy { ViewModelProvider(this).get(SampleViewModel::class.java) }
    private lateinit var mBinding: ActivitySampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sample)

    }


    private fun initData() {
        viewModel.getData()
    }
}