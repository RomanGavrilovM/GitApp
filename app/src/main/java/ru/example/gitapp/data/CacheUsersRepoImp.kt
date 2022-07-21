package ru.example.gitapp.data

import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.domain.UserRepo
import io.reactivex.rxjava3.core.Single

class CacheUsersRepoImp : UserRepo {
    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        TODO("Not yet implemented")
    }

    override fun getUsers(): Single<List<UserEntity>> {
        TODO("Not yet implemented")
    }
}