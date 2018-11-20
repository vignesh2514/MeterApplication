package com.inesh.ineshmeter.userinterface

import android.content.Intent
import android.os.Bundle

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.inesh.ineshmeter.R
import com.inesh.ineshmeter.globaladapters.Adapteronlytext
import com.inesh.ineshmeter.globalclass.SharedPrefUtils
import com.inesh.ineshmeter.listclass.ListTextOnly
import com.inesh.ineshmeter.userinterface.authentication.Login
import com.inesh.ineshmeter.userinterface.authentication.OtpScreen
import com.inesh.ineshmeter.volleyglobal.APIController
import com.inesh.ineshmeter.volleyglobal.ServiceVolley

import kotlinx.android.synthetic.main.activity_meterlistscreen.*
import kotlinx.android.synthetic.main.content_meterlistscreen.*

import org.json.JSONObject

class MeterListScreen : AppCompatActivity() {
    val path = "meterreadinglist.php"
    val params = JSONObject()
    private val service = ServiceVolley()
    private val apiController = APIController(service)
    private var datagatherAdapter: Adapteronlytext? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meterlistscreen)
        setSupportActionBar(toolbar)


        // Now get the support action bar
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.title = "Hi! "+SharedPrefUtils.getuserName()

        // Set action bar/toolbar sub title
        actionBar.subtitle = SharedPrefUtils.getmobileNumber()
        val phonenumber = SharedPrefUtils.getmobileNumber()
        RvMeterList.layoutManager = LinearLayoutManager(this)
        RvMeterList.hasFixedSize()
        getindustryvalue(phonenumber)

//Bgetdetails.setOnClickListener {
//
//
//        if (meternumb=="")
//        {
//            Toast.makeText(this@MeterListScreen, "Please Select any Meter in list", Toast.LENGTH_SHORT).show()
//
//        }
//        else
//        {
//            val intent = Intent(this, MasterPage::class.java)
//            intent.putExtra("meter_num", meternumb)
//            startActivity(intent)
//        }
//
//
//
//}

    }

    private fun getindustryvalue(phonenumber: String?) {

        apiController.get("$path?mobileNumber=$phonenumber") {

            val strResp = it.toString()
            val jsonObj = JSONObject(strResp)
            // val jsonObjnj = jsonObj.getJSONObject("result")
            val jsonArray = jsonObj.getJSONArray("result")
            val partList = ArrayList<ListTextOnly>()

            for (i in 0 until jsonArray.length()) {
                val jsonInner = jsonArray.getJSONObject(i)
                partList.add(
                    ListTextOnly(
                        jsonInner.getString("meterrefid"),
                        "Meter No: " + jsonInner.getString("meterno"),
                        "Status:" + jsonInner.getString("status"),
                        false
                    )
                )
            }

            datagatherAdapter = Adapteronlytext(partList) { partItem: ListTextOnly -> partItemClicked(partItem) }
            RvMeterList.adapter = datagatherAdapter

        }

    }

    private fun partItemClicked(partItem: ListTextOnly) {

        datagatherAdapter!!.notifyDataSetChanged()
       val meternumb=partItem.itemName
        if (meternumb=="")
        {
            Toast.makeText(this@MeterListScreen, "Please Select any Meter in list", Toast.LENGTH_SHORT).show()

        }
        else
        {
            Toast.makeText(this@MeterListScreen, meternumb, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MasterPage::class.java)
            intent.putExtra("meter_num", meternumb)
            startActivity(intent)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.logout, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {

            R.id.action_logout -> {
                Toast.makeText(this@MeterListScreen, "User has been Successfully Logout", Toast.LENGTH_SHORT).show()
                SharedPrefUtils.clearTokens()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}