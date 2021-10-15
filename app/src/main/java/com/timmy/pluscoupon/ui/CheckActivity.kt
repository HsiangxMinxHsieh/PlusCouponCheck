package com.timmy.pluscoupon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.timmy.pluscoupon.R
import com.timmy.pluscoupon.databinding.ActivitySampleBinding
import com.timmy.pluscoupon.viewmodel.CheckViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckActivity : AppCompatActivity() {
    private val viewModel: CheckViewModel by lazy { ViewModelProvider(this).get(CheckViewModel::class.java) }
    private lateinit var mBinding: ActivitySampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sample)

    }


    private fun initData() {
        viewModel.getData()
    }
}