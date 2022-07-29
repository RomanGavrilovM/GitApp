package ru.example.gitapp.data.retrofit

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import ru.example.gitapp.domain.UserEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntityDto(
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
) : Parcelable {

    fun convertDtoToUserEntity() = UserEntity(login, id, avatarUrl, type, siteAdmin)


    companion object {
        fun convertUserEntityToDto(userEntity: UserEntity): UserEntityDto {
            return UserEntityDto(
                userEntity.login,
                userEntity.id,
                userEntity.avatarUrl,
                userEntity.type,
                userEntity.siteAdmin
            )
        }
    }
}