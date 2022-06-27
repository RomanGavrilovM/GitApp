package ru.example.gitapp.data

import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.domain.UserRepo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private val gitApi = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://api.github.com/")
    .client(OkHttpClient.Builder().build())
    .build()
    .create(UserRepo::class.java)

class NetUserRepoImp : UserRepo {
    override suspend fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        onSuccess(gitApi.getNetData())
    }

    override suspend fun getNetData(): List<UserEntity> = gitApi.getNetData()
}