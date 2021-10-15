package com.timmy.pluscoupon.viewmodel

import android.app.Application
import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timmy.pluscoupon.R
import com.timmy.pluscoupon.database.CheckData
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import timber.log.Timber
import java.util.*

class CheckViewModel @ViewModelInject constructor(val context: Application, private val getRawRepository: GetRawRepository) : ViewModel() {

    /** 輸入框文字，用於雙向綁定EditText物件 */
    val xmlContent: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    private val checkData: CheckData by lazy { getRawRepository.getData() }

    private val _showResult by lazy { MutableLiveData<String>() }

    val showResult: LiveData<String> = _showResult

    /** 檢查紀錄列表，用於儲存與更新檢查紀錄 */
    private val _liveCheckRecord: MutableLiveData<TreeSet<String>> by lazy { MutableLiveData<TreeSet<String>>() }
    val liveCheckRecord: LiveData<TreeSet<String>> = _liveCheckRecord

    /** 關閉軟鍵盤通知，用於通知Activity關閉軟鍵盤 */
    private val _liveHideKeyBoard: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val liveHideKeyBoard: LiveData<Boolean> = _liveHideKeyBoard

    init {
        checkData
        initLiveValue()
    }

    private fun initLiveValue() {
        _liveCheckRecord.postValue(TreeSet()) // 初始化塞值(不然裡面是null)
        _showResult.postValue("")
    }

    /**檢查code內是否在checkData內*/
    fun check(code: String?) {
        xmlContent.postValue("")
        _liveHideKeyBoard.postValue(true)

        if (code.isNullOrEmpty()) {
            showEmptyEnter()
            return
        }

        Timber.e("to check code =>$code")

        val result = checkForResult(code)

        _liveCheckRecord.postValue(_liveCheckRecord.value?.apply { add(code) })

        if (result.isNotEmpty()) {
            _showResult.postValue(result.toMeetResult())
        } else {
            showNotMeetResult()
        }
    }

    private fun checkForResult(code: String): MutableList<String> {
        val result = mutableListOf<String>()
        checkData.rawData.forEach { type ->
            type.codes.forEach codeCheck@{ check ->
                if (judgeIsMeetTheCriteria(check, code)) {
                    result.add("${type.name}[$check]")
                    return@codeCheck
                }
            }
        }
        return result
    }

    private fun showEmptyEnter() {
        _showResult.postValue(context.getString(R.string.empty_enter))
    }

    private fun showNotMeetResult() {
        _showResult.postValue(context.getString(R.string.not_meet).format(xmlContent.value))
    }

    /**判斷是否有對的方法，檢查check如果是三碼：三碼需全符合才true
     * 如果是兩碼，必須code後兩碼符合便符合→Code有三碼，末兩碼符合便true
     * code有兩碼，全符合才true 。
     * */
    private fun judgeIsMeetTheCriteria(check: String, code: String): Boolean {
        return if (check.length == 3 && code == check)
            true
        else if (code.length == 3 && code.substring(1..2) == check) { // check.length == 2
            true
        } else code == check
    }

    private fun List<String>.toMeetResult() =
        this.toString().apply {
            return context.getString(R.string.meet_criteria).format(this.substring(1..this.length - 2))
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


