package com.inovasiti.kucingapp

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import java.sql.Timestamp

class SharePrefHelper {


    //singleton sharePref
    companion object{
        private const val PREF_TIME = "pref_time"

        private var prefs: SharedPreferences? = null

        @Volatile private var instance: SharePrefHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): SharePrefHelper = instance ?: synchronized(LOCK){
            instance ?: buildHelper(context).also{
                instance = it
            }
        }

        private fun buildHelper(context: Context): SharePrefHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharePrefHelper()
        }
    }

    //storing time in SP
    fun saveUpdateTime(timestamp: Long){
        prefs?.edit(commit = true){
            putLong(PREF_TIME, timestamp)
        }
    }

    fun getUpdateTime() = prefs?.getLong(PREF_TIME,0)
}