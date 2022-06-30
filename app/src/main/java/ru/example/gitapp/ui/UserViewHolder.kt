package ru.example.gitapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.example.gitapp.R
import ru.example.gitapp.domain.UserEntity
import ru.example.gitapp.databinding.ItemUserBinding

class UserViewHolder(
    parent: ViewGroup,
    onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private val binding = ItemUserBinding.bind(itemView)

    init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    fun bind(userEntity: UserEntity) {
        binding.userItemAvatar.load(userEntity.avatarUrl)
        binding.userItemId.text = userEntity.id.toString()
        binding.userItemLogin.text = userEntity.login
    }
}