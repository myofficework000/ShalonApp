package com.example.shaloonapp.model.repository

import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.model.dto.User
import kotlinx.coroutines.flow.Flow


interface IRepository {

    //function for User Dao
    suspend fun getAllUser(): Flow<ResultState<List<User>>>
    suspend fun getUser(userId:Int):Flow<ResultState<User>>
    suspend fun insertUser(user: User)
    suspend fun insertMultipleUser(users: List<User>)

    //function for Service Dao
    suspend fun getAllService(): Flow<ResultState<List<Service>>>
    suspend fun insertMultipleService(services: List<Service>)


    //function for Barber Dao

    suspend fun insertBarber(barber: Barber)
    suspend fun insertMultipleBarber(barber: List<Barber>)


    //


    suspend fun getAllBarber(): Flow<ResultState<List<Barber>>>
    suspend fun getBarberById(barberId: Int): Flow<ResultState<Barber>>
}
