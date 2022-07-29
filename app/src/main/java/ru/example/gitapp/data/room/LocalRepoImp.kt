package ru.example.gitapp.data.room

import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.domain.UserRepo
import io.reactivex.rxjava3.core.Single

class LocalRepoImp(private val userDao: UserDao) : UserRepo {
    override fun getUsers(): Single<List<UserEntity>> =
        userDao.getAllUsers()
            .map { roomUserList -> roomUserList.map { it.convertDaoToUserEntity() } }
}