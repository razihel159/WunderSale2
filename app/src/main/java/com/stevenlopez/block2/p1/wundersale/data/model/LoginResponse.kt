package com.stevenlopez.block2.p1.wundersale.data.model

import android.content.Context
import android.content.SharedPreferences

data class LoginResponse(
    val error: Boolean,
    val message: String,
    val user: User,
    val token: String
) {
    companion object {
        private const val PREF_NAME = "login_prefs"
        private const val KEY_TOKEN = "token"

        // Function to save token to SharedPreferences
        fun saveToken(context: Context, token: String) {
            val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            prefs.edit().putString(KEY_TOKEN, token).apply()
        }

        // Function to retrieve token from SharedPreferences
        fun getToken(context: Context): String? {
            val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            return prefs.getString(KEY_TOKEN, null)
        }
    }
}
