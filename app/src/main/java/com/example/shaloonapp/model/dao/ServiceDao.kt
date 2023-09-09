package com.example.shaloonapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shaloonapp.model.Constant.SERVICE_TABLE_NAME
import com.example.shaloonapp.model.dto.Service

@Dao
interface ServiceDao {
    @Query("SELECT * FROM $SERVICE_TABLE_NAME")
    fun getAllService():List<Service>

    @Query("SELECT * FROM $SERVICE_TABLE_NAME WHERE  serviceId = :serviceId")
    fun getServiceById(serviceId: Int): Service

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertService(service: Service)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultipleService(services: List<Service>)
}