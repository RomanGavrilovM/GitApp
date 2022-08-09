package ru.example.gitapp.domain

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET

interface UserRepo {

    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

    fun getUsers(): Single<List<UserEntity>>


}
