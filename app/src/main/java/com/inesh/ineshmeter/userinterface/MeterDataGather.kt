package com.inesh.ineshmeter.userinterface

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.inesh.ineshmeter.R
import com.inesh.ineshmeter.globaladapters.Adapteronlytext
import com.inesh.ineshmeter.listclass.ListTextOnly
import com.inesh.ineshmeter.volleyglobal.APIController
import com.inesh.ineshmeter.volleyglobal.ServiceVolley
import kotlinx.android.synthetic.main.activity_meter_data_gather.*
import org.json.JSONObject

class MeterDataGather : AppCompatActivity() {
    val path="account_service/auth/accounts/meta/accounts"
    val params = JSONObject()
    private val service = ServiceVolley()
    private val apiController = APIController(service)
    private var datagatherAdapter:Adapteronlytext?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meter_data_gather)
        val phonenumber = intent.getStringExtra("mobile_number")
        RvMeterList.layoutManager = GridLayoutManager(this, 2)
        RvMeterList.hasFixedSize()
        getindustryvalue()

    }

    private fun getindustryvalue() {

        apiController.get(path) {

            val strResp = it.toString()
            val jsonObj = JSONObject(strResp)
            val jsonObjnj = jsonObj.getJSONObject("data")
            val jsonArray = jsonObjnj.getJSONArray("industry")
            val partList = ArrayList<Adapteronlytext>()

            for(i in 0 until jsonArray.length())
            {
                val jsonInner = jsonArray.getJSONObject(i)
                partList.add(Adapteronlytext(jsonInner.getString("industryId"), jsonInner.getString("title"),false))
            }

            datagatherAdapter= Adapteronlytext(partList) { partItem: ListTextOnly -> partItemClicked(partItem) }
            RvMeterList.adapter=datagatherAdapter

        }

    }

    private fun partItemClicked(partItem: ListTextOnly) {
       

        datagatherAdapter!!.notifyDataSetChanged()

    }
}
