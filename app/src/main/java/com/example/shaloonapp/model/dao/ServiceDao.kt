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
    suspend fun getAllService():List<Service>

    @Query("SELECT * FROM $SERVICE_TABLE_NAME WHERE  serviceId = :serviceId")
    suspend fun getServiceById(serviceId: Int): Service

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertService(service: Service)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultipleService(services: List<Service>)
}