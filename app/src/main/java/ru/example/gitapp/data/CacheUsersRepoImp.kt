package ru.example.gitapp.data

import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.domain.UserRepo
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@ActivityScoped
class CacheUsersRepoImp @Inject constructor(
    private val localRepo: UserRepo,
    private val remoteRepo: UserRepo
    ) : UserRepo {
    override fun getUsers(): Single<List<UserEntity>> {
        return Single.concat(localRepo.getUsers(), remoteRepo.getUsers())
            .filter { t: List<UserEntity> -> t.isNotEmpty() }
            .first(mutableListOf())
    }
}