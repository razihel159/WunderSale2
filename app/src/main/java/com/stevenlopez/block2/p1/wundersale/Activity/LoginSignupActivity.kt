package com.stevenlopez.block2.p1.wundersale.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.data.RetrofitHelper
import com.stevenlopez.block2.p1.wundersale.data.model.LoginResponse
import com.stevenlopez.block2.p1.wundersale.fragments.loginSignup.introductionFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginSignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup)

        val buttonLogin: Button = findViewById(R.id.buttonLogin)
        val emaillogin: EditText = findViewById(R.id.edEmailLogin)
        val passwordlogin: EditText = findViewById(R.id.edPasswordLogin)


        buttonLogin.setOnClickListener {

             val email = emaillogin.text.toString().trim()
             val password = passwordlogin.text.toString().trim()

            if(email.isEmpty()){
                emaillogin.error = "Please enter email"
                emaillogin.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                passwordlogin.error = "Please enter password"
                passwordlogin.requestFocus()
                return@setOnClickListener
            }

            RetrofitHelper.instance.login(email, password)
                .enqueue(object: Callback<LoginResponse>{
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if(!response.body()?.error!!){
                            val intent = Intent(applicationContext,ShoppingActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                })
        }
    }
}