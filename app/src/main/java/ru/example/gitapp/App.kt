package ru.example.gitapp

import android.app.Application
import android.content.Context
import ru.example.gitapp.data.FakeUsersRepoImp
import ru.example.gitapp.data.NetUserRepoImp

class App:Application() {
    //  val userRepo by lazy { FakeUsersRepoImp() }
    val userRepo by lazy { NetUserRepoImp() }
}

val Context.app: App get() = applicationContext as App