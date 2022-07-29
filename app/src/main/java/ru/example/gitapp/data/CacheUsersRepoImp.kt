package ru.example.gitapp.data

import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.domain.UserRepo
import io.reactivex.rxjava3.core.Single

class CacheUsersRepoImp(
    private val localRepo: UserRepo,
    private val remoteRepo: UserRepo
) : UserRepo {

    override fun getUsers(): Single<List<UserEntity>> {
        return Single.concat(localRepo.getUsers(), remoteRepo.getUsers())
            .filter { t: List<UserEntity> -> t.isNotEmpty() }
            .first(mutableListOf())
    }


}