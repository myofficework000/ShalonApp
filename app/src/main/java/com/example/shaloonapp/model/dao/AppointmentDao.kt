package com.example.shaloonapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shaloonapp.model.Constant.APPOINTMENT_TABLE_NAME
import com.example.shaloonapp.model.dto.Appointment

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM $APPOINTMENT_TABLE_NAME")
    suspend fun getAllAppointment():List<Appointment>

    @Query("SELECT * FROM $APPOINTMENT_TABLE_NAME WHERE  appointmentId = :appointmentId")
    suspend fun getAppointmentById(appointmentId: Int): Appointment

    @Query("SELECT * FROM $APPOINTMENT_TABLE_NAME WHERE  userId = :userId")
    suspend fun getAppointmentByUserId(userId: Int): List<Appointment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointment: Appointment) :Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultipleAppointment(appointment: List<Appointment>)
}