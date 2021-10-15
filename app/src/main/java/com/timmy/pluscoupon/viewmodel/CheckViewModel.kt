package com.timmy.pluscoupon.viewmodel

import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timmy.pluscoupon.database.CheckData
import com.timmy.pluscoupon.util.toJson
import timber.log.Timber


class CheckViewModel @ViewModelInject constructor(private val getRawRepository: GetRawRepository) : ViewModel() {

    /** 輸入框文字，用於雙向綁定EditText物件 */
    val xmlContent: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    private val checkData: CheckData by lazy { getRawRepository.getData() }

    private val _showResult by lazy{MutableLiveData<String>()}
    val showResult: LiveData<String> = _showResult

    init {
        _showResult.postValue("")
    }

    fun getData() {
        Timber.e("取到的資料是=>${checkData.toJson()}")
    }

    fun check(code: String?) {
        Timber.e("to check code =>$code")



    }


    /** Enter可以直接搜尋 為了要兼容模擬器時電腦鍵盤可以用，花費不少功夫在嘗試...Orz */
    val editorActionListener: TextView.OnEditorActionListener =
        TextView.OnEditorActionListener { _, actionId, event ->
            if ((actionId == KeyEvent.KEYCODE_UNKNOWN || actionId == KeyEvent.KEYCODE_HOME) && event?.action != KeyEvent.ACTION_DOWN) {
                check(xmlContent.value)
            }
            true
        }

    companion object {

        @JvmStatic
        @BindingAdapter("onEditorActionListener")
        fun bindOnEditorActionListener(editText: EditText, editorActionListener: TextView.OnEditorActionListener?) {
            editText.setOnEditorActionListener(editorActionListener)
        }

    }
}