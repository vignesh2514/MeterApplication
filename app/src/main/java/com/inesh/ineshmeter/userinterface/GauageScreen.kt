package com.inesh.ineshmeter.userinterface

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.inesh.ineshmeter.R
import com.inesh.ineshmeter.volleyglobal.APIController
import com.inesh.ineshmeter.volleyglobal.ServiceVolley
import kotlinx.android.synthetic.main.fragment_meter_details.*
import org.json.JSONObject

class GauageScreen : AppCompatActivity() {
    val meterDetailApi="meterdetails.php"
    val service = ServiceVolley()
    val apiController = APIController(service)
    val params = JSONObject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gauage_screen)

        val meternumber= intent.getStringExtra("meter_num")
        startapicall(meternumber)


    }


    private fun startapicall(meter_num: String?) {
        params.put("meterno", meter_num)
        apiController.post(meterDetailApi, params) {
            try {
                val exits= it!!.getBoolean("exits")

                if (exits)
                {

                    val meterdetail=it.getJSONObject("meterdetails")
                    val systemrtc=meterdetail.getString("systemrtc")
                    val meterrtc=meterdetail.getString("meterrtc")
                    val rv=meterdetail.getString("rv")
                    val yv=meterdetail.getString("yv")
                    val bv=meterdetail.getString("bv")
                    val kw=meterdetail.getString("kw")
                    val mdkw=meterdetail.getString("mdkw")
                    val kwh=meterdetail.getString("kwh")
                    val rc=meterdetail.getString("rc")
                    val yc=meterdetail.getString("yc")
                    val bc=meterdetail.getString("bc")
                    val instant=meterdetail.getString("instant")
                    val freq=meterdetail.getString("freq")
                    val kvah=meterdetail.getString("kvah")

                    TvSysRtc.text=systemrtc
                    TvMeterRtc.text=meterrtc
                    TvIstV.text="R- $rv Y- $yv B- $bv"
                    TvIstC.text="R- $rc Y- $yc B- $bc"
                    TvKw.text=kw
                    TvKwh.text=kwh




                }
                else
                {
                    val errormessage = it.getString("message")
                    //    Toast.makeText(this@Login, errormessage, Toast.LENGTH_SHORT).show()

                }

            } catch (e: Exception) {


            }
        }


    }
}
