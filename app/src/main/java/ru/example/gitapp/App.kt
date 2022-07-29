package ru.example.gitapp

import android.app.Application
import android.content.Context
import ru.example.dilibrary.Di
import ru.example.dilibrary.DiImpl
import ru.example.gitapp.di.DiModule

class App : Application() {
    lateinit var di: Di

    override fun onCreate() {
        super.onCreate()
        di = DiImpl(app).apply { DiModule(this) }

    }

}

val Context.app: App get() = applicationContext as App