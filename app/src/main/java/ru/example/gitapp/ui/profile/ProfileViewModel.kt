package ru.example.gitapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.example.gitapp.domain.UserEntity

class ProfileViewModel(private val user: UserEntity) : ProfileContract.ViewModel {

    override val profileLiveData: LiveData<UserEntity> = MutableLiveData()

    override fun setProfile() {
        profileLiveData.toMutable().postValue(user)
    }

    private fun <T> LiveData<T>.toMutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("not MutableLiveData")
    }
}