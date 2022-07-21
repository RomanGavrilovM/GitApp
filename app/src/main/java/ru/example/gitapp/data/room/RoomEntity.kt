package ru.example.gitapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomEntity(
    @PrimaryKey
    val login: String,

    val id: Long,

    val avatarUrl: String,

    val type: String,

    val siteAdmin: Boolean
)