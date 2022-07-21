package ru.example.gitapp.domain

import ru.example.gitapp.data.room.RoomUserEntity

data class UserEntity(

    val login: String,

    val id: Long,

    val avatarUrl: String,

    val type: String,

    val siteAdmin: Boolean
)
