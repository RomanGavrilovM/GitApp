package ru.example.gitapp.ui.users

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.example.gitapp.domain.UserEntity

class UserAdapter(
    private val onItemClick:(UserEntity) -> Unit
) : RecyclerView.Adapter<UserViewHolder>() {

    private val data = mutableListOf<UserEntity>()

    init {
        setHasStableIds(true)
    }
    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(parent){
            onItemClick(data[it])
        }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    private fun getItem(position: Int) = data[position]
    override fun getItemCount() = data.size
    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<UserEntity>){
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }
}