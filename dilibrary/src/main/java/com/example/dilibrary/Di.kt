package ru.example.dilibrary

import android.content.Context
import kotlin.reflect.KClass

interface Di {
    fun <T : Any> get(clazz: KClass<T>): T
    fun <T : Any> add(clazz: KClass<T>, dependency: Any)
    fun getContext(): Context

}