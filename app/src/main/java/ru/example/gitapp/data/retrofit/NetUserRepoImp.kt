package ru.example.gitapp.data.retrofit

import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.domain.UserRepo
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@ActivityScoped
class NetUserRepoImp @Inject constructor(val githubApi: GithubApi) : UserRepo {
    override fun getUsers(): Single<List<UserEntity>> = githubApi.getNetData().map { users ->
        users.map {
            it.convertDtoToUserEntity()
        }
    }

}