package com.inesh.ineshmeter.userinterface.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.inesh.ineshmeter.R
import com.inesh.ineshmeter.userinterface.MeterDataGather
import com.inesh.ineshmeter.volleyglobal.APIController
import com.inesh.ineshmeter.volleyglobal.ServiceVolley
import kotlinx.android.synthetic.main.activity_otp_screen.*
import org.json.JSONObject

class OtpScreen : Activity() {
    val login_app="user_otp_verify.php"
    val service = ServiceVolley()
    val apiController = APIController(service)
    val params = JSONObject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_screen)
        val phonenumber = intent.getStringExtra("mobile_number")



        Bverifyotp.setOnClickListener {


            otpapireq(phonenumber,Etotp.text.toString())

        }

    }

    private fun otpapireq(phonenumber: String?, eotp: String) {

        params.put("smobile_num", phonenumber)
        params.put("otp", eotp)

        apiController.post(login_app, params) {
            try {
                val exits= it!!.getBoolean("exits")
                if (exits)
                {
                    val user_detail= it.getJSONObject("users_detail")
                    val mobile_number=user_detail.getString("mobile_number")
                    val intent = Intent(this, MeterDataGather::class.java)
                    intent.putExtra("mobile_number", mobile_number)
                    startActivity(intent)
                }
                else
                {
                    val errormessage = it.getString("message")
                    Toast.makeText(this@OtpScreen, errormessage, Toast.LENGTH_SHORT).show()

                }

            } catch (e: Exception) {


            }
        }

    }
}
