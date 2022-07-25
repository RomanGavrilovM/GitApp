package ru.example.gitapp.data.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    fun getNetData(): Single<List<UserEntityDto>>

}