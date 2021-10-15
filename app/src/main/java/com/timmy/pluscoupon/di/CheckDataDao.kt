package com.timmy.pluscoupon.di

import android.content.Context
import com.timmy.pluscoupon.database.CheckData
import com.timmy.pluscoupon.util.toDataBean
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.io.*
import java.lang.StringBuilder
import javax.inject.Singleton


/**讀取資料的Dao*/
@Module
@InstallIn(ApplicationComponent::class)
class CheckDataDao {

    private lateinit var context: Context

    constructor()

    constructor(context: Context) {
        this.context = context
    }

    @Singleton
    @Provides
    fun getRawJson(id: Int): CheckData {
        val stream: InputStream? = context.resources?.openRawResource(id)
        return read(stream).toDataBean(CheckData::class.java) ?: CheckData()
    }

    private fun read(stream: InputStream?): String {
        return read(stream, "utf-8")
    }

    private fun read(`is`: InputStream?, encode: String? = "utf-8"): String {
        if (`is` != null) {
            try {
                val reader = BufferedReader(InputStreamReader(`is`, encode))
                val sb = StringBuilder()
                var line: String? = null
                while (reader.readLine().also { line = it } != null) {
                    sb.append(
                        """
                            $line
                            
                            """.trimIndent()
                    )
                }
                `is`.close()
                return sb.toString()
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return ""
    }
}