package ru.example.gitapp.ui.profile

import ru.example.gitapp.domain.UserEntity
import io.reactivex.rxjava3.core.Observable

interface ProfileContract {

    interface ViewModel {
        val profileLiveData: Observable<UserEntity>

        fun setProfile()
    }
}