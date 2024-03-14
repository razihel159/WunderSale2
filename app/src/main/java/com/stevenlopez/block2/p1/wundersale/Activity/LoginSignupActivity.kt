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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginSignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup)

        val buttonLogin: Button = findViewById(R.id.buttonLogin)
        val emailLogin: EditText = findViewById(R.id.edEmailLogin)
        val passwordLogin: EditText = findViewById(R.id.edPasswordLogin)


        buttonLogin.setOnClickListener {

             val email = emailLogin.text.toString().trim()
             val password = passwordLogin.text.toString().trim()

            if(email.isEmpty()){
                emailLogin.error = "Please enter email"
                emailLogin.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                passwordLogin.error = "Please enter password"
                passwordLogin.requestFocus()
                return@setOnClickListener
            }

            RetrofitHelper.instance.login(email, password)
                .enqueue(object: Callback<LoginResponse>{
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        val responseBody = response.body()
                        if(response.isSuccessful && responseBody != null) {
                                val intent = Intent(applicationContext,ShoppingActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                        } else {
                            Toast.makeText(applicationContext, "Incorrect Email or Password", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })

        }
    }
}