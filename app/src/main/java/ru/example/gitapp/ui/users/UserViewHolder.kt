package ru.example.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.example.gitapp.R
import ru.example.gitapp.databinding.ItemUserBinding
import ru.example.gitapp.domain.UserEntity
import java.io.File

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

        binding.userIdTextView.text = userEntity.id.toString()
        binding.userLoginTextView.text = userEntity.login
    }
}
