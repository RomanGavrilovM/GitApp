package ru.example.gitapp.di

import ru.example.gitapp.data.CacheUsersRepoImp
import ru.example.gitapp.data.retrofit.GithubApi
import ru.example.gitapp.data.retrofit.NetUserRepoImp
import ru.example.gitapp.data.room.LocalRepoImp
import ru.example.gitapp.data.room.UserDao
import ru.example.gitapp.data.room.UserDatabase
import ru.example.gitapp.domain.UserRepo
import ru.example.gitapp.ui.users.UsersViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {


    single { "https://api.github.com/" }

    single<GithubApi> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(get<String>())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(GithubApi::class.java)
    }


    single<UserDatabase> { UserDatabase.getDatabase(androidContext()) }
    single<UserDao> {get<UserDatabase>().userDao() }

    single<UserRepo>(named("remote")) { NetUserRepoImp(get()) }
    single<UserRepo>(named("local")) { LocalRepoImp(get<UserDao>()) }

    single<UserRepo>(named("cache")) {
        CacheUsersRepoImp(
            get(named("local")),
            get(named("remote")))
    }

    viewModel() { UsersViewModel(get(named("cache"))) }


}