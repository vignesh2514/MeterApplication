package com.inesh.ineshmeter.userinterface

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.inesh.ineshmeter.R
import com.inesh.ineshmeter.globalclass.SharedPrefUtils
import com.inesh.ineshmeter.userinterface.authentication.Login

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashScreen)
        super.onCreate(savedInstanceState)
        if (!SharedPrefUtils.isUserLoggedIn())
        {
            startActivity(Intent(this@SplashScreen, Login::class.java))
            finish()
            }
        else
        {
            startActivity(Intent(this@SplashScreen, MeterListScreen::class.java))
            finish()
        }

    }
}
