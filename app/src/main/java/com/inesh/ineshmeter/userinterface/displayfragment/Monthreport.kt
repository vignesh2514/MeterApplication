package com.inesh.ineshmeter.userinterface.displayfragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.inesh.ineshmeter.R
import com.inesh.ineshmeter.globaladapters.Adaptermultitext
import com.inesh.ineshmeter.globalclass.SharedPrefUtils
import com.inesh.ineshmeter.listclass.Listmultitext
import com.inesh.ineshmeter.userinterface.Dayreport
import com.inesh.ineshmeter.volleyglobal.APIController
import com.inesh.ineshmeter.volleyglobal.ServiceVolley
import kotlinx.android.synthetic.main.fragment_monthreport.*
import kotlinx.android.synthetic.main.fragment_monthreport.view.*
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Monthreport : Fragment() {
    val path = "monthvisereport.php"
    val params = JSONObject()
    private val service = ServiceVolley()
    private val apiController = APIController(service)
    private var datagatherAdapter: Adaptermultitext? = null
    companion object {
        fun newInstance(): Monthreport = Monthreport()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_monthreport, container, false)
        v.rv_monthreport.layoutManager = LinearLayoutManager(context)
        v.rv_monthreport.hasFixedSize()
       val meternum=SharedPrefUtils.getmeterno()
        getindustryvalue(meternum,v)
        return  v

    }
    private fun getindustryvalue(meternum: String?, v: View) {

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
                        jsonInner.getString("month"),jsonInner.getString("nummonth"),"","",
                        false
                    )
                )
            }

            datagatherAdapter = Adaptermultitext(partList) { partItem: Listmultitext -> partItemClicked(partItem) }
            v.rv_monthreport.adapter = datagatherAdapter

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
            val intent = Intent(context, Dayreport::class.java)
            startActivity(intent)
//        }

    }

}
