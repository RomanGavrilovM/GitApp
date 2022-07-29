package ru.home.gitapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM RoomUserEntity")
    fun getAllUsers(): Single<List<RoomUserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserList(users:List<RoomUserEntity>)

}