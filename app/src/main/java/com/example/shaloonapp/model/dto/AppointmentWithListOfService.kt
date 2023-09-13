package com.example.shaloonapp.model.dto

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class AppointmentWithListOfService(
    @Embedded val appointment: Appointment,
    
    @Relation(
        parentColumn = "appointmentId",
        entityColumn = "serviceId",
        associateBy = Junction(AppointmentWithServiceCrossRef::class)
    )
    val listOfServices: List<Service>,

    @Relation(parentColumn = "barberId", entityColumn = "barberId")
    val barber: Barber
)