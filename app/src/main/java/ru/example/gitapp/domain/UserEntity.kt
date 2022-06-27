package ru.example.gitapp.domain

import com.google.gson.annotations.SerializedName

data class UserEntity(

    @SerializedName("login")
    val login: String,

    @SerializedName("id")
    val id: Long,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("site_admin")
    val siteAdmin: Boolean
)