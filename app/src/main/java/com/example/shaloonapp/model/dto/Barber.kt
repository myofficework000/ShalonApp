package com.example.shaloonapp.model.dto

import android.support.annotation.DrawableRes
import java.util.UUID

data class Barber(

val barberId: UUID= UUID.randomUUID(),
val firstName: String,
val lastName: String,
val experience: String,
val rating: Float,
@DrawableRes val image:Int

)
