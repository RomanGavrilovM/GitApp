package ru.example.gitapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.example.gitapp.R
import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.databinding.UserItemBinding

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
) {
    private val binding = UserItemBinding.bind(itemView)

    fun bind(userEntity: UserEntity) {
        binding.userItemAvatar.load(userEntity.avatarUrl)
        binding.userItemId.text = userEntity.id.toString()
        binding.userItemLogin.text = userEntity.login
    }
}