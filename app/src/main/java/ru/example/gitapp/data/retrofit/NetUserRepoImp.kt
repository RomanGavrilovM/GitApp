package ru.example.gitapp.data.retrofit

import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.domain.UserRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private val gitApi = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .baseUrl("https://api.github.com/")
    .client(OkHttpClient.Builder().build())
    .build()
    .create(GithubApi::class.java)


class NetUserRepoImp : UserRepo {

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        gitApi.getNetData().subscribeBy(
            onSuccess = { users ->
                onSuccess.invoke(users.map { it.convertDtoToUserEntity() })
            },
            onError = { error ->
                onError?.invoke(error)
            }
        )

    }

    override fun getUsers(): Single<List<UserEntity>> = gitApi.getNetData().map { users ->
        users.map {
            it.convertDtoToUserEntity()
        }
    }

}