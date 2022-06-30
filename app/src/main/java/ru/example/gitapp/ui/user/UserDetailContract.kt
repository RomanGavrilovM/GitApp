package ru.example.gitapp.ui.user

import ru.example.gitapp.domain.UserEntity

interface UserDetailContract {

    interface View {
        fun showUserDetail(user:UserEntity)
    }

    interface Presenter {
        fun attach(view: View)

        fun detach()

        fun loadData()
    }
}