package com.inesh.ineshmeter.globalclass

import android.content.Context
import android.content.SharedPreferences

object SharedPrefUtils {


    private const val PREF_NAME= "ineshmeterapp"
    private const val MOBILE_NUMBER = "mobileNumber"

    private lateinit var mPrefs: SharedPreferences


    fun init(context: Context) {
        mPrefs = context.getSharedPreferences(
            PREF_NAME, Context.MODE_PRIVATE)

    }

    fun setmobileNumber(mobileNumber: String) {

        mPrefs.edit()
            .putString(MOBILE_NUMBER,mobileNumber)

            .apply()
    }

    fun getmobileNumber(): String? {
        return mPrefs.getString(MOBILE_NUMBER, "")
    }


}