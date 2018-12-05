package com.inesh.ineshmeter.volleyglobal

import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.inesh.ineshmeter.globalclass.BackendVolley
import org.json.JSONObject


//service request
class ServiceVolley : ServiceInterface {
    val TAG = ServiceVolley::class.java.simpleName
val basepath="http://www.smarte.co.in/dinesh_testing/"
//val basepath="http:118.91.232.232:89/aac/"

    override fun post(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        val jsonObjReq = object : JsonObjectRequest(Method.POST, basepath+ path, params,
            Response.Listener<JSONObject> { response ->
                Log.d(TAG, "/post request OK! Response: $response")
                completionHandler(response)
            },
            Response.ErrorListener { error ->

                VolleyLog.e(TAG, "/post request fail! Error: ${error.message}")
                val networkResponse = error.networkResponse
                if (networkResponse?.data != null) {
                    val responseerror =JSONObject( String(networkResponse.data))
                    completionHandler(responseerror)
                }


            }) {
            @Throws(AuthFailureError::class)

            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                return headers
            }
        }

        BackendVolley.instance?.addToRequestQueue(jsonObjReq, TAG)
    }


    override fun patch(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        val jsonObjReq = object : JsonObjectRequest(Method.PATCH, basepath+ path, params,
            Response.Listener<JSONObject> { response ->
                Log.d(TAG, "/post request OK! Response: $response")
                completionHandler(response)
            },
            Response.ErrorListener { error ->

                VolleyLog.e(TAG, "/post request fail! Error: ${error.message}")
                val networkResponse = error.networkResponse
                if (networkResponse?.data != null) {
                    val responseerror =JSONObject( String(networkResponse.data))
                    completionHandler(responseerror)
                }


            }) {
            @Throws(AuthFailureError::class)

            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                return headers
            }
        }

        BackendVolley.instance?.addToRequestQueue(jsonObjReq, TAG)
    }

    override fun get(path: String, completionHandler: (response: String?) -> Unit) {
        val jsonObjReq = object : StringRequest(Method.GET, basepath+ path,
            Response.Listener<String> { response ->
                Log.d(TAG, "/get request OK! Response: $response")
                completionHandler(response)
            },
            Response.ErrorListener { error ->

                VolleyLog.e(TAG, "/get request fail! Error: ${error.message}")
                val networkResponse = error.networkResponse
                if (networkResponse?.data != null) {
                    val responseerror = String(networkResponse.data)
                    completionHandler(responseerror)

                }


            }) {
            @Throws(AuthFailureError::class)

            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                return headers
            }
        }

        BackendVolley.instance?.addToRequestQueue(jsonObjReq, TAG)
    }




}