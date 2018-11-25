package com.inesh.ineshmeter.globalclass

import android.content.Context
import android.content.SharedPreferences

object SharedPrefUtils {


    private const val PREF_NAME= "ineshmeterapp"
    private const val MOBILE_NUMBER = "mobileNumber"
    private const val USER_NAME = "userName"
    private const val METER_NO = "meterno"
    private const val METER_DAY = "meterday"


    private lateinit var mPrefs: SharedPreferences


    fun init(context: Context) {
        mPrefs = context.getSharedPreferences(
            PREF_NAME, Context.MODE_PRIVATE)

    }

    fun setmobileNumber(mobileNumber: String?) {

        mPrefs.edit()
            .putString(MOBILE_NUMBER,mobileNumber)

            .apply()
    }

    fun setuserName(mobileNumber: String?) {

        mPrefs.edit()
            .putString(USER_NAME,mobileNumber)

            .apply()
    }

    fun clearTokens() {
        mPrefs.edit().remove(MOBILE_NUMBER).remove(MOBILE_NUMBER).apply()
        mPrefs.edit().clear().apply()
    }

    fun setmeterno(meterno: String?) {

        mPrefs.edit()
            .putString(METER_NO,meterno)

            .apply()
    }
    fun getmeterno(): String? {
        return mPrefs.getString(METER_NO, "")
    }
    fun getmobileNumber(): String? {
        return mPrefs.getString(MOBILE_NUMBER, "")
    }
    fun getuserName(): String? {
        return mPrefs.getString(USER_NAME, "")
    }

    fun isUserLoggedIn(): Boolean {
        return mPrefs.getString(MOBILE_NUMBER, "").isNotBlank()
    }


    fun setmeterday(meterday: String?) {

        mPrefs.edit()
            .putString(METER_DAY,meterday)

            .apply()
    }
    fun getmeterday(): String? {
        return mPrefs.getString(METER_DAY, "")
    }
}