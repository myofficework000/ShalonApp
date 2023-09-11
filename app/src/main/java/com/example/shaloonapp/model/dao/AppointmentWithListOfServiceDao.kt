package com.example.shaloonapp.model.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.shaloonapp.model.Constant.APPOINTMENT_TABLE_NAME
import com.example.shaloonapp.model.dto.AppointmentWithListOfService

@Dao
interface AppointmentWithListOfServiceDao {

    @Transaction
    @Query("SELECT * FROM $APPOINTMENT_TABLE_NAME")
    suspend fun getAllAppointmentWithListOfService(): List<AppointmentWithListOfService>


    @Transaction
    @Query(
        "SELECT * FROM $APPOINTMENT_TABLE_NAME WHERE appointmentId = :appointmentId")
    suspend fun getAppointmentWithListOfServiceById(appointmentId: Int): List<AppointmentWithListOfService>

    @Transaction
    @Query("SELECT * FROM $APPOINTMENT_TABLE_NAME WHERE  userId = :userId")
    suspend fun getAppointmentWithListOfServiceByUserId(userId: Int)
    : List<AppointmentWithListOfService>

}