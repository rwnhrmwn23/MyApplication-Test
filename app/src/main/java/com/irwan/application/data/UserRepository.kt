package com.irwan.application.data

import com.irwan.application.domain.User
import com.irwan.application.network.UsersApi

class UsersRepository(private val api: UsersApi) {
    suspend fun fetchUsers(): List<User> = api.getUsers()
}