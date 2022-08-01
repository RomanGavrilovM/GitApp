package ru.example.gitapp.data.retrofit

import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.domain.UserRepo
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetUserRepoImp(val githubApi: GithubApi) : UserRepo {
    override fun getUsers(): Single<List<UserEntity>> = githubApi.getNetData().map { users ->
        users.map {
            it.convertDtoToUserEntity()
        }
    }

}