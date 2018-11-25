package com.inesh.ineshmeter.userinterface

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import com.inesh.ineshmeter.R
import com.inesh.ineshmeter.globaladapters.Adaptermultitext
import com.inesh.ineshmeter.globalclass.SharedPrefUtils
import com.inesh.ineshmeter.listclass.Listmultitext
import com.inesh.ineshmeter.volleyglobal.APIController
import com.inesh.ineshmeter.volleyglobal.ServiceVolley

import kotlinx.android.synthetic.main.activity_dayreport.*
import kotlinx.android.synthetic.main.content_dayreport.*
import org.json.JSONObject

class Dayreport : AppCompatActivity() {
    val path = "dayonmonth.php"
    val params = JSONObject()
    private val service = ServiceVolley()
    private val apiController = APIController(service)
    private var datagatherAdapter: Adaptermultitext? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dayreport)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rv_monthreport.layoutManager = LinearLayoutManager(this)
        rv_monthreport.hasFixedSize()
        val meternum= SharedPrefUtils.getmeterno()
        val day =SharedPrefUtils.getmeterday()
        getindustryvalue(meternum,day)
    }

    private fun getindustryvalue(meternum: String?, day: Any?) {

        apiController.get("$path?meterNo=$meternum&month=$day") {

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
                        jsonInner.getString("day"),"KVAH : ${jsonInner.getString("kvah")}",
                        "KWH:${jsonInner.getString("kwh")}","Close : ${jsonInner.getString("closecurrent")}",
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
        val meternumb=partItem.itemName
//        if (meternumb=="")
//        {
//          //  Toast.makeText(this@MeterListScreen, "Please Select any Meter in list", Toast.LENGTH_SHORT).show()
//
//        }
//        else
//        {
//            Toast.makeText(this@MeterListScreen, meternumb, Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, MasterPage::class.java)
//            intent.putExtra("meter_num", meternumb)
//            startActivity(intent)
//        }

    }

}
