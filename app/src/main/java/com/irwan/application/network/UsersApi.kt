package com.irwan.application.network

import com.irwan.application.domain.User
import retrofit2.http.GET

interface UsersApi {
    @GET("users")
    suspend fun getUsers(): List<User>
}