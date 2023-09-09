package com.example.shaloonapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shaloonapp.model.Constant.USER_TABLE_NAME
import com.example.shaloonapp.model.dto.User

@Dao
interface UserDao {
    @Query("SELECT * FROM $USER_TABLE_NAME")
    fun getAllUser():List<User>

    @Query("SELECT * FROM $USER_TABLE_NAME WHERE  userId = :userId")
    fun getUser(userId:Int):User

    @Insert
    fun insertUser(user: User)
}