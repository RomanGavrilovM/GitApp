package ru.example.gitapp

import android.app.Application
import android.content.Context
import ru.example.gitapp.data.room.UserDatabase
import ru.example.gitapp.di.AppModule
import ru.example.gitapp.domain.UserRepo
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var database: UserDatabase

/*  @AppModule.CacheRepo
    @Inject
    lateinit var userRepo: UserRepo*/

}

val Context.app: App get() = applicationContext as App