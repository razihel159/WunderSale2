package com.stevenlopez.block2.p1.wundersale.data.model

data class LoginResponse(
    val error: Boolean,
    val message: String,
    val user: User,
    val token: String
)
