package ru.example.gitapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.domain.UserRepo
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsersViewModel(val repository: UserRepo) : ViewModel() {

    private val _userList: MutableSharedFlow<List<UserEntity>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val userList: Flow<List<UserEntity>> = _userList

    fun requestUserList() {
        viewModelScope.launch {
            _userList.emit(repository.getNetData())
        }
    }

    class UsersViewModelFactory(private val repository: UserRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = UsersViewModel(repository) as T
    }
}