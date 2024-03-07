package com.stevenlopez.block2.p1.wundersale.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.stevenlopez.block2.p1.wundersale.R
import com.stevenlopez.block2.p1.wundersale.data.Api
import com.stevenlopez.block2.p1.wundersale.data.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
    }
}