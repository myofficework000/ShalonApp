package com.example.shaloonapp.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shaloonapp.model.Constant.SERVICE_TABLE_NAME

@Entity(tableName = SERVICE_TABLE_NAME)
data class Service(
    @PrimaryKey(autoGenerate = true)
    val serviceId: Int= 0,

    val name: String ="",
    val price: Int =0,
    val description: String ="",
    val durationInMinute: Int = 1,
    val imgURL: String = ""
)
