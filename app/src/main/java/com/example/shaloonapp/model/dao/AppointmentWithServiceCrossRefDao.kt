package com.example.shaloonapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shaloonapp.model.Constant.APPOINTMENT_WITH_SERVICE_TABLE_NAME
import com.example.shaloonapp.model.dto.AppointmentWithServiceCrossRef

@Dao
interface AppointmentWithServiceCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointmentWithServiceCrossRef(
        appointmentWithServiceCrossRef: AppointmentWithServiceCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultipleAppointmentWithServiceCrossRef(
        appointmentWithServiceCrossRefs: List<AppointmentWithServiceCrossRef>)

    @Query("SELECT * FROM $APPOINTMENT_WITH_SERVICE_TABLE_NAME")
    suspend fun getAllAppointmentWithServiceCrossRef():List<AppointmentWithServiceCrossRef>
}