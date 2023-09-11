package com.example.shaloonapp.model.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.shaloonapp.model.Constant.APPOINTMENT_TABLE_NAME

enum class PaymentMethod{
    BANK, CASH
}
@Entity(tableName = APPOINTMENT_TABLE_NAME,
    foreignKeys = [
        ForeignKey( entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["userId"]),
        ForeignKey( entity = Barber::class,
            parentColumns = ["barberId"],
            childColumns = ["barberId"])
    ])
data class Appointment(
    @PrimaryKey(autoGenerate = true)
    val appointmentId: Int =0,

    val userId: Int,
    val barberId: Int,
    val services: List<Service>,
    val appointmentDate: String ="",
    val appointmentTime: String ="",
    val serviceCharge: Double,
    val status: String = "",
    val paymentMode: String= ""
)
