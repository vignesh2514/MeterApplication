package com.inesh.ineshmeter.userinterface.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.inesh.ineshmeter.R
import com.inesh.ineshmeter.globalclass.SharedPrefUtils
import com.inesh.ineshmeter.userinterface.MeterListScreen
import com.inesh.ineshmeter.volleyglobal.APIController
import com.inesh.ineshmeter.volleyglobal.ServiceVolley
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class Login : Activity() {
    val login_app="user_login.php"
    val service = ServiceVolley()
    val apiController = APIController(service)
    val params = JSONObject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Blogin.setOnClickListener {

startapicall(EtMobileNumber.text.toString(),EtPassword.text.toString())
        }

        TvSignup.setOnClickListener {
            startActivity(Intent(this@Login, SignUp::class.java))
        }
    }

    private fun startapicall(phonenumber: String, password: String) {
        params.put("smobile_num", phonenumber)
        params.put("spass", password)


        apiController.post(login_app, params) {
            try {
                val exits= it!!.getBoolean("exits")

if (exits)
{
    val successmessage = it.getString("message")
    Toast.makeText(this@Login, successmessage, Toast.LENGTH_SHORT).show()
    val userdetail=it.getJSONObject("users_detail")
    val mobile_number=userdetail.getString("mobile_number")
    val userName=userdetail.getString("userName")

    if(successmessage=="VO")
    {
        val intent = Intent(this, OtpScreen::class.java)
        intent.putExtra("mobile_number", mobile_number)
        startActivity(intent)
    }
    else
    {
        SharedPrefUtils.setmobileNumber(mobile_number)
        SharedPrefUtils.setuserName(userName)
        val intent = Intent(this, MeterListScreen::class.java)
        intent.putExtra("mobile_number", mobile_number)
        startActivity(intent)
        finish()
    }




}
                else
{
    val errormessage = it.getString("message")
    Toast.makeText(this@Login, errormessage, Toast.LENGTH_SHORT).show()

}

            } catch (e: Exception) {


            }
        }


    }
}
