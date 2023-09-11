package com.example.shaloonapp.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shaloonapp.model.Constant.USER_TABLE_NAME

@Entity(tableName = USER_TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int=0,

    val firstName: String ="",
    val lastName: String = "",
    val email: String ="",
    val password: String ="",
)
