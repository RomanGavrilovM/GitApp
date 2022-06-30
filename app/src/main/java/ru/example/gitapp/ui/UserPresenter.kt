package ru.example.gitapp.ui

import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.domain.UserRepo

class UserPresenter(

    private val userRepo: UserRepo

) : UserContract.Presenter {
    private var view: UserContract.View? = null

    private var userList: List<UserEntity>? = null

    private var inProgress: Boolean = false


    override fun attach(view: UserContract.View) {
        this.view = view
        view.showPorgress(inProgress)
        userList?.let { view.showUsers(it) }
    }

    override fun detach() {
        view = null
    }

    override suspend fun onRefresh() {
        loadData()
    }

    private suspend fun loadData() {
        view?.showPorgress(true)
        inProgress = true
        userRepo.getNetData().let {
            view?.showPorgress(false)
            view?.showUsers(it)
            userList = it
            inProgress = false
        }
    }
}