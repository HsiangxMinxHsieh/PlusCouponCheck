package com.timmy.pluscoupon.ui

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.timmy.pluscoupon.R
import com.timmy.pluscoupon.databinding.ActivityPlusCouponBinding
import com.timmy.pluscoupon.viewmodel.CheckViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Layout:[R.layout.activity_plus_coupon]
 * */

@AndroidEntryPoint
class CheckActivity : AppCompatActivity() {
    private val viewModel: CheckViewModel by lazy { ViewModelProvider(this).get(CheckViewModel::class.java) }
    private val activity = this
    private lateinit var mBinding: ActivityPlusCouponBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        initObservable()
    }

    private fun initObservable() {
        // 搜尋歷史紀錄更新 觀察者
        viewModel.liveCheckRecord.observe(activity, {
            mBinding.edtInput.setAdapter((ArrayAdapter(activity, android.R.layout.select_dialog_item, it.toList())))
        })

        // 隱藏鍵盤 觀察者，用於ViewModel搜尋前隱藏螢幕鍵盤。
        viewModel.liveHideKeyBoard.observe(activity, {
            activity.hideSoftKeyboard()
        })

    }

    private fun initViewModel() {
        mBinding = (DataBindingUtil.setContentView(this, R.layout.activity_plus_coupon) as ActivityPlusCouponBinding).apply{
            lifecycleOwner = activity
            vm = viewModel
        }
    }

    private fun Activity.hideSoftKeyboard() {
        val inputMethodManager: InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(mBinding.root.windowToken, 0)
    }

}