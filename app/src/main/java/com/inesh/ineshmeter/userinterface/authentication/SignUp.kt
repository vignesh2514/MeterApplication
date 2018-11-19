package com.inesh.ineshmeter.userinterface.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.inesh.ineshmeter.R
import com.inesh.ineshmeter.volleyglobal.APIController
import com.inesh.ineshmeter.volleyglobal.ServiceVolley
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUp : Activity() {
    val login_app="user_signup.php"
    val service = ServiceVolley()
    val apiController = APIController(service)
    val params = JSONObject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        Bsignup.setOnClickListener {

            if (EtName.text.isEmpty() && EtMnum.text.isEmpty() && EtPass.text.isEmpty() && Etrepass.text.isEmpty() && EtMeterNum.text.isEmpty()) {
                Toast.makeText(this@SignUp, "Please Fill all fields", Toast.LENGTH_SHORT).show()

            } else {
                startsignupapicall(
                    EtName.text.toString(),
                    EtMnum.text.toString(),
                    EtPass.text.toString(),
                    EtMeterNum.text.toString()
                )

            }
        }
    }

    private fun startsignupapicall(name: String, mobilenum: String, pass: String, meternumber: String) {

        params.put("smobile_num", mobilenum)
        params.put("smeternum", meternumber)
        params.put("spass", pass)
        params.put("name", name)

        apiController.post(login_app, params) {
            try {
                val exits= it!!.getBoolean("exits")
                if (exits)
                {
                    val user_detail= it.getJSONObject("users_detail")
                    val mobile_number=user_detail.getString("mobile_number")
                    val intent = Intent(this, OtpScreen::class.java)
                    intent.putExtra("mobile_number", mobile_number)
                    startActivity(intent)
                }
                else
                {
                    val errormessage = it.getString("message")
                    Toast.makeText(this@SignUp, errormessage, Toast.LENGTH_SHORT).show()

                }

            } catch (e: Exception) {


            }
        }

    }

    }


