package ru.example.gitapp.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import ru.example.gitapp.app
import ru.example.gitapp.databinding.ActivityUserDatailBinding
import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.ui.DETAIL_USER
import ru.example.gitapp.ui.UserContract
import ru.example.gitapp.ui.UserPresenter

class UserDetailActivity : AppCompatActivity(),UserDetailContract.View {

    private lateinit var binding: ActivityUserDatailBinding

    private lateinit var presenter: UserDetailContract.Presenter

    private lateinit var user:UserEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDatailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<UserEntity>(DETAIL_USER)?.let { user = it }

        presenter = extractPresenter()
        presenter.attach(this)

        initView(user)


    }

    private fun initView(user: UserEntity?) {
        user?.let { showUserDetail(it) }
    }

    override fun showUserDetail(user: UserEntity) {

        binding.apply {
            userDetailActivityAvatar.load(user?.avatarUrl)
            userDetailActivityLogin.text = user?.login
            userDetailActivityId.text = user?.id.toString()
            userDetailActivityType.text = user?.type
        }
    }


    private fun extractPresenter(): UserDetailContract.Presenter {
        return lastCustomNonConfigurationInstance as? UserDetailContract.Presenter
            ?: UserDetailPresenter(user)
    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): UserDetailContract.Presenter? {
        return presenter
    }
}