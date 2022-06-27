package ru.example.gitapp.domain

import retrofit2.http.GET

interface UserRepo {

    suspend fun getUsers(
        onSuccess:(List<UserEntity>) ->Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    @GET("users")
    suspend fun getNetData(): List<UserEntity>
}