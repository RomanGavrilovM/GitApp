package ru.example.gitapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.example.gitapp.app
import ru.example.gitapp.data.retrofit.UserEntityDto
import ru.example.gitapp.data.room.UserDatabase
import ru.example.gitapp.databinding.ActivityMainBinding
import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.ui.profile.ProfileActivity
import ru.example.gitapp.ui.users.UserAdapter
import ru.example.gitapp.ui.users.UserContract
import ru.example.gitapp.ui.users.UsersViewModel
import ru.example.gitapp.utils.getImagePath
import ru.example.gitapp.utils.onLoadBitmap
import io.reactivex.rxjava3.disposables.CompositeDisposable

const val DETAIL_USER = "DETAIL_USER"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val adapter = UserAdapter { user ->
        userViewModel.onUserClick(user)
    }

    private lateinit var userViewModel: UserContract.ViewModel

    private val viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initViewModel()

    }


    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }

    private fun openProfileScreen(userEntity: UserEntity) {
        val intent = Intent(this.app, ProfileActivity::class.java).apply {
            putExtra(DETAIL_USER, UserEntityDto.convertUserEntityToDto(userEntity))
        }
        startActivity(intent)
    }

    private fun getViewModel(): UserContract.ViewModel {
        return lastCustomNonConfigurationInstance as? UserContract.ViewModel
            ?: UsersViewModel(app.userRepo)
    }

    override fun onRetainCustomNonConfigurationInstance(): UserContract.ViewModel {
        return userViewModel
    }

    private fun initViews() {
        binding.mainActivityRefreshButton.setOnClickListener {
            userViewModel.onRefresh()
        }
        initRecycleView()
        showProgress(false)

    }

    private fun initViewModel() {
        userViewModel = getViewModel()

        viewModelDisposable.addAll(

            userViewModel.progressLiveData.subscribe { showProgress(it) },
            userViewModel.usersLiveData.subscribe {
                showUsers(it)
                // test(it)
            },
            userViewModel.usersNetUpdateLiveData.subscribe {
                showUsers(it)
                setCacheData(it)
            },
            userViewModel.errorLiveData.subscribe { showError(it) },
            userViewModel.openProfileLiveData.subscribe { openProfileScreen(it) },

            )
    }


    fun setCacheData(userList: List<UserEntity>) {
        userViewModel.onSaveImage(userList)
        userViewModel.usersBitmap.subscribe { bitmapList ->
            var tmpUserList: MutableList<UserEntity> = mutableListOf()
            for (i in 0..userList.size - 1) {
                onLoadBitmap(
                    app,
                    bitmapList[i],
                    userList[i].login
                )
                val internalPath = getImagePath(app, userList[i].login)
                val updatedUser = UserEntity(
                    userList[i].login,
                    userList[i].id,
                    "$internalPath.png",
                    userList[i].type,
                    userList[i].siteAdmin
                )
                tmpUserList.add(updatedUser)
            }
            updateLocalRepo(app.database, tmpUserList)
        }

    }


    private fun updateLocalRepo(db: UserDatabase, userList: List<UserEntity>) {
        userViewModel.onNewData(db, userList)
    }


    private fun initRecycleView() {
        binding.mainActivityRecycle.layoutManager = LinearLayoutManager(this)
        binding.mainActivityRecycle.adapter = adapter
    }


    private fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)
    }

    private fun showError(throwable: Throwable) {
        Snackbar.make(binding.root, throwable.message.toString(), Snackbar.LENGTH_SHORT).show()
    }

    private fun showProgress(inProgress: Boolean) {
        binding.mainActivityProgressBar.isVisible = inProgress
        binding.mainActivityRecycle.isVisible = !inProgress
    }
}