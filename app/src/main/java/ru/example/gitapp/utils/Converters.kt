package ru.example.gitapp.utils

import ru.example.gitapp.data.room.RoomUserEntity
import ru.example.gitapp.domain.UserEntity


fun convertUserEntityToDAO(userEntity: UserEntity) =
    RoomUserEntity(
        userEntity.login,
        userEntity.id,
        userEntity.avatarUrl,
        userEntity.type,
        userEntity.siteAdmin
    )