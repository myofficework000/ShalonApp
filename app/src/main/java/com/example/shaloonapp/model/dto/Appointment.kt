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
            childColumns = ["barberId"]),

    ])
data class Appointment(
    val userId: Long,
    val barberId: Long?,
    val appointmentDate: String ="",
    val appointmentTime: String ="",
    val serviceCharge: Double =0.0,
    val status: String = "",
    val paymentMode: String= "",

    @PrimaryKey(autoGenerate = true)
    val appointmentId: Long =0,
)
