package ru.example.gitapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.example.gitapp.UsersViewModel
import ru.example.gitapp.data.NetUserRepoImp
import ru.example.gitapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val adapter = UserAdapter()

    private val userViewModel: UsersViewModel by viewModels{
        UsersViewModel.UsersViewModelFactory(NetUserRepoImp())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initRecycleView()
    }

    private fun initViews() {
        binding.mainActivityRefreshButton.setOnClickListener {
            loadData()
        }
    }

    private fun loadData() {
        showProgress(true)
        userViewModel.requestUserList()
        onDataLoaded()
    }

    private fun onDataLoaded () {
        this.lifecycle.coroutineScope.launchWhenStarted {
            userViewModel.userList.collect(){ userList ->
                adapter.setData(userList)
                showProgress(false)
            }
        }
    }

    private fun onError(throwable: Throwable){
        Snackbar.make(binding.root, throwable.message.toString(), Snackbar.LENGTH_SHORT).show()
    }

    private fun initRecycleView() {
        binding.mainActivityRecycle.layoutManager = LinearLayoutManager(this)
        binding.mainActivityRecycle.adapter = adapter
    }

    private fun showProgress(inProgress:Boolean){
        binding.mainActivityProgressBar.isVisible = inProgress
        binding.mainActivityRecycle.isVisible= !inProgress
    }
}