package com.example.shaloonapp.model.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.shaloonapp.model.Constant.APPOINTMENT_WITH_SERVICE_TABLE_NAME

@Entity(tableName = APPOINTMENT_WITH_SERVICE_TABLE_NAME,
    foreignKeys = [
        ForeignKey( entity = Appointment::class,
            parentColumns = ["appointmentId"],
            childColumns = ["appointmentId"]),
        ForeignKey( entity = Service::class,
            parentColumns = ["serviceId"],
            childColumns = ["serviceId"]),

    ])
data class AppointmentWithServiceCrossRef(
    @PrimaryKey
    val appointmentId: Int ,
    @PrimaryKey
    val serviceId: Int ,
)
