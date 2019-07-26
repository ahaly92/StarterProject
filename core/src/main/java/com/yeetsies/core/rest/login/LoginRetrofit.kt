package com.yeetsies.core.rest.login

import com.yeetsies.core.rest.RetrofitClient

object LoginRetrofit {
    private const val BASE_URL = ""

    val loginService: LoginService by lazy {
        RetrofitClient.createRestApi(
            BASE_URL,
            LoginService::class.java
        )
    }
}
