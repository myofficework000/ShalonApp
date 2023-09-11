package com.example.shaloonapp.model.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.shaloonapp.model.Constant.APPOINTMENT_WITH_SERVICE_TABLE_NAME
import com.example.shaloonapp.model.dto.AppointmentWithListOfService

@Dao
interface AppointmentWithListOfServiceDao {

    @Transaction
    @Query("SELECT * FROM $APPOINTMENT_WITH_SERVICE_TABLE_NAME")
    fun getAppointmentWithListOfService(): List<AppointmentWithListOfService>


}