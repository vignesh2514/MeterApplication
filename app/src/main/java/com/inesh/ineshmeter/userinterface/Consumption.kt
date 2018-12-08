package com.inesh.ineshmeter.userinterface

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.inesh.ineshmeter.R
import com.inesh.ineshmeter.globaladapters.Adaptermultitext
import com.inesh.ineshmeter.globalclass.SharedPrefUtils
import com.inesh.ineshmeter.listclass.Listmultitext
import com.inesh.ineshmeter.volleyglobal.APIController
import com.inesh.ineshmeter.volleyglobal.ServiceVolley

import kotlinx.android.synthetic.main.activity_consumption.*
import kotlinx.android.synthetic.main.content_consumption.*
import kotlinx.android.synthetic.main.fragment_monthreport.view.*
import org.json.JSONObject

class Consumption : AppCompatActivity() {
    val path = "consumption.php"
    val params = JSONObject()
    private val service = ServiceVolley()
    private val apiController = APIController(service)
    private var datagatherAdapter: Adaptermultitext? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumption)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        rv_monthreport.layoutManager = LinearLayoutManager(this)
        rv_monthreport.hasFixedSize()
        val meternum=SharedPrefUtils.getmeterno()
        getindustryvalue(meternum)
    }
    private fun getindustryvalue(meternum: String?) {

        apiController.get("$path?meterNo=$meternum") {

            val strResp = it.toString()
            val jsonObj = JSONObject(strResp)
            // val jsonObjnj = jsonObj.getJSONObject("result")
            val jsonArray = jsonObj.getJSONArray("result")
            val partList = ArrayList<Listmultitext>()

            for (i in 0 until jsonArray.length()) {
                val jsonInner = jsonArray.getJSONObject(i)
                partList.add(
                    Listmultitext(
                        "",
                        jsonInner.getString("month"),jsonInner.getString("nummonth"),jsonInner.getString("closedvalue"),"",
                        false
                    )
                )
            }
            datagatherAdapter = Adaptermultitext(partList) { partItem: Listmultitext -> partItemClicked(partItem) }
            rv_monthreport.adapter = datagatherAdapter

        }

    }


    private fun partItemClicked(partItem: Listmultitext) {
        datagatherAdapter!!.notifyDataSetChanged()
        val day=partItem.itemSubName
        SharedPrefUtils.setmeterday(day)
//        if (meternumb=="")
//        {
//          //  Toast.makeText(this@MeterListScreen, "Please Select any Meter in list", Toast.LENGTH_SHORT).show()
//
//        }
//        else
//        {
//            Toast.makeText(this@MeterListScreen, meternumb, Toast.LENGTH_SHORT).show()
        val intent = Intent(this@Consumption, Dayreport::class.java)
        startActivity(intent)
//        }

    }
}
