package ru.example.gitapp.di

import ru.example.gitapp.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,DataBaseModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}