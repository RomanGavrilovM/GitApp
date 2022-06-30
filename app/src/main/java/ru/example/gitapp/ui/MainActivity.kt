package ru.example.gitapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.example.gitapp.app
import ru.example.gitapp.data.NetUserRepoImp
import ru.example.gitapp.databinding.ActivityMainBinding
import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.ui.user.UserDetailActivity

const val DETAIL_USER = "DETAIL_USER"

class MainActivity : AppCompatActivity(), UserContract.View {
    private lateinit var binding: ActivityMainBinding


    private val adapter = UserAdapter { user ->

        Snackbar.make(binding.root, user.login, Snackbar.LENGTH_SHORT).show()
        val intent = Intent(this.app, UserDetailActivity::class.java).apply {
            putExtra(DETAIL_USER, user)
        }
        startActivity(intent)
    }

    private val userViewModel: UsersViewModel by viewModels {
        UsersViewModel.UsersViewModelFactory(NetUserRepoImp())
    }

    private lateinit var presenter: UserContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        presenter = extractPresenter()
        presenter.attach(this)

    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): UserContract.Presenter? {
        return presenter
    }

    private fun extractPresenter(): UserContract.Presenter {
        return lastCustomNonConfigurationInstance as? UserContract.Presenter
            ?: UserPresenter(app.userRepo)
    }

    private fun initViews() {
        binding.mainActivityRefreshButton.setOnClickListener {
            this.lifecycle.coroutineScope.launchWhenStarted {
                presenter.onRefresh()
            }
        }
        initRecycleView()
    }

    private fun loadData() {
        userViewModel.requestUserList()


    }

    private fun initRecycleView() {
        binding.mainActivityRecycle.layoutManager = LinearLayoutManager(this)
        binding.mainActivityRecycle.adapter = adapter
    }


    override fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)

    }

    override fun showError(throwable: Throwable) {
        Snackbar.make(binding.root, throwable.message.toString(), Snackbar.LENGTH_SHORT).show()
    }

    override fun showPorgress(inProgress: Boolean) {
        binding.mainActivityProgressBar.isVisible = inProgress
        binding.mainActivityRecycle.isVisible = !inProgress
    }
}