package com.example.shaloonapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shaloonapp.model.Constant.BARBER_TABLE_NAME
import com.example.shaloonapp.model.dto.Barber

@Dao
interface BarberDao {
    @Query("SELECT * FROM ${BARBER_TABLE_NAME}")
    fun getAllBarber(): List<Barber>

    @Query("SELECT * FROM ${BARBER_TABLE_NAME} WHERE  barberId = :barberId")
    fun getBarberById(barberId:Int): Barber

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBarber(barber: Barber)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultipleBarber(barber: List<Barber>)
}