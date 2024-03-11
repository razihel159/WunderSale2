package com.stevenlopez.block2.p1.wundersale.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stevenlopez.block2.p1.wundersale.R


class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val navController = findNavController(R.id.shoppingHostFragment)
        bottomNavigation.setupWithNavController(navController)

    }
}