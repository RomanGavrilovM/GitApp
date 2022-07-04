package ru.example.gitapp.ui.profile

import androidx.lifecycle.LiveData
import ru.example.gitapp.domain.UserEntity

interface ProfileContract {
    interface ViewModel {
        val profileLiveData: LiveData<UserEntity>
        fun setProfile()
    }
}