package com.example.shaloonapp.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shaloonapp.model.Constant.BARBER_TABLE_NAME

@Entity(tableName = BARBER_TABLE_NAME)
data class Barber(
    @PrimaryKey(autoGenerate = true)
    val barberId: Long =0,

    val firstName: String ="",
    val lastName: String ="",
    val experience: String ="",
    val rating: Double =0.0,
    val imgUrL: String =""
)