package com.inesh.ineshmeter.userinterface.displayfragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.inesh.ineshmeter.R
import com.inesh.ineshmeter.globalclass.SharedPrefUtils
import com.inesh.ineshmeter.userinterface.MeterListScreen
import com.inesh.ineshmeter.userinterface.authentication.OtpScreen
import com.inesh.ineshmeter.volleyglobal.APIController
import com.inesh.ineshmeter.volleyglobal.ServiceVolley
import kotlinx.android.synthetic.main.fragment_meter_details.*
import kotlinx.android.synthetic.main.fragment_meter_details.view.*
import org.json.JSONObject

class MeterDetails : Fragment() {

    val meterDetailApi="meterdetails.php"
    val service = ServiceVolley()
    val apiController = APIController(service)
    val params = JSONObject()
    companion object {
        fun newInstance(): MeterDetails = MeterDetails()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_meter_details, container, false)
       val  meter_num = arguments!!.getString("meter_num")
        startapicall(meter_num,view)

        return view
    }
    private fun startapicall(meter_num: String?, view: View) {
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
