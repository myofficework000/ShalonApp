package com.example.shaloonapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shaloonapp.model.Constant.USER_TABLE_NAME
import com.example.shaloonapp.model.dto.User

@Dao
interface UserDao {
    @Query("SELECT * FROM $USER_TABLE_NAME")
    suspend fun getAllUser():List<User>

    @Query("SELECT * FROM $USER_TABLE_NAME WHERE  userId = :userId")
    suspend fun getUser(userId:Int):User

    @Query("SELECT * FROM $USER_TABLE_NAME WHERE  email = :email AND password = :password")
    suspend fun getUserForCredentials(email:String,password:String):User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
}