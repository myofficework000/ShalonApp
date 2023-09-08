package com.example.shaloonapp.model.dto

data class Service(
    val id: Long= 0L,
    val title: String ="",
    val price: Int =0,
    val description: String ="",
    val durationInMinute: Int = 1,
    val imgURL: String = ""
)
