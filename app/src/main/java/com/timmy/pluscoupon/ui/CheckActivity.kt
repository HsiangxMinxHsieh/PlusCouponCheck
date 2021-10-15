package com.timmy.pluscoupon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.timmy.pluscoupon.R
import com.timmy.pluscoupon.databinding.ActivityPlusCouponBinding
import com.timmy.pluscoupon.viewmodel.CheckViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CheckActivity : AppCompatActivity() {
    private val viewModel: CheckViewModel by lazy { ViewModelProvider(this).get(CheckViewModel::class.java) }
    private val activity = this
    private lateinit var mBinding: ActivityPlusCouponBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        initObserverble()
    }

    private fun initObserverble() {
        viewModel.showResult.observe(activity,{
            Timber.e("取得的結果是=>$it")
        })
    }

    private fun initViewModel() {
        mBinding = (DataBindingUtil.setContentView(this, R.layout.activity_plus_coupon) as ActivityPlusCouponBinding).apply{
            lifecycleOwner = activity
            vm = viewModel
        }
    }

}