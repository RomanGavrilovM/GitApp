package ru.example.gitapp.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import ru.example.gitapp.app
import ru.example.gitapp.data.retrofit.UserEntityDto
import ru.example.gitapp.databinding.ActivityProfileBinding
import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.ui.DETAIL_USER
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.io.File

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var user: UserEntity

    private lateinit var profileViewModel: ProfileContract.ViewModel

    private val viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getParcelableExtra<UserEntityDto>(DETAIL_USER)
            ?.let { user = it.convertDtoToUserEntity() }
        initViewModel()
        initView(user)


    }

    private fun initViewModel() {
        profileViewModel = getViewModel()

        viewModelDisposable.addAll(
            profileViewModel.profileLiveData.subscribe() { showUserProfile(it) }
        )

    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }

    private fun getViewModel(): ProfileContract.ViewModel {
        return lastCustomNonConfigurationInstance as? ProfileContract.ViewModel
            ?: ProfileViewModel(user)
    }
    override fun onRetainCustomNonConfigurationInstance(): ProfileContract.ViewModel {
        return profileViewModel
    }
    private fun initView(user: UserEntity?) {
        user?.let { showUserProfile(it) }
    }

    private fun showUserProfile(user: UserEntity) {
        binding.apply {
            if (user.avatarUrl.contains("http")) {
                profileAvatarImageView.load(user.avatarUrl)
            } else profileAvatarImageView.load(File(user.avatarUrl))

            profileLoginTextView.text = user?.login
            profileIdTextView.text = user?.id.toString()
            profileTypeTextView.text = user?.type
        }
    }
}