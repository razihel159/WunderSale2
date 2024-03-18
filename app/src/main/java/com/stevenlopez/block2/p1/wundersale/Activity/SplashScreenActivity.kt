package com.stevenlopez.block2.p1.wundersale.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.stevenlopez.block2.p1.wundersale.R

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_LENGTH = 2000L // Splash screen delay in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Using Handler to delay the start of MainActivity
        Handler().postDelayed({
            // Start MainActivity after the specified delay
            val mainIntent = Intent(this@SplashScreenActivity, LoginSignupActivity::class.java)
            startActivity(mainIntent)
            finish() // Close the splash screen activity
        }, SPLASH_DISPLAY_LENGTH)
    }
}