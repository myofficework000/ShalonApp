package com.example.shaloonapp.model.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.shaloonapp.model.Constant.APPOINTMENT_WITH_SERVICE_TABLE_NAME

@Entity(tableName = APPOINTMENT_WITH_SERVICE_TABLE_NAME,
    foreignKeys = [
        ForeignKey( entity = Appointment::class,
            parentColumns = ["appointmentId"],
            childColumns = ["appointmentId"]),
        ForeignKey( entity = Service::class,
            parentColumns = ["serviceId"],
            childColumns = ["serviceId"]),

    ],
    primaryKeys = ["appointmentId", "serviceId"])
data class AppointmentWithServiceCrossRef(
    val appointmentId: Long ,
    val serviceId: Long ,
)
