package ru.example.dilibrary

import android.content.Context
import kotlin.reflect.KClass

class DiImpl( private  val context: Context) : Di {

    private val dependenciesHolder = HashMap<KClass<*>, Any>()

    override fun <T : Any> get(clazz: KClass<T>): T {
        if (dependenciesHolder.containsKey(clazz)) {
            return dependenciesHolder[clazz] as T
        } else {
            throw  IllegalArgumentException("Have not class in graph")
        }

    }

    override fun <T : Any> add(clazz: KClass<T>,dependency: Any) {
        dependenciesHolder[clazz] = dependency
    }

    override fun getContext(): Context {
        return  context
    }


}
