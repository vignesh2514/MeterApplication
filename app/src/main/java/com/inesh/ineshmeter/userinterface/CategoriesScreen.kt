package com.inesh.ineshmeter.userinterface

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.inesh.ineshmeter.R
import kotlinx.android.synthetic.main.activity_categories_screen.*

class CategoriesScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_screen)

        val meternumber= intent.getStringExtra("meter_num")


        binshist.setOnClickListener {

            val intent = Intent(this, CategoriesScreen::class.java)
            intent.putExtra("meter_num", meternumber)
            startActivity(intent)

        }
        bloadbalance.setOnClickListener {


            val intent = Intent(this, GauageScreen::class.java)
            intent.putExtra("meter_num", meternumber)
            startActivity(intent)

        }

    }
}
