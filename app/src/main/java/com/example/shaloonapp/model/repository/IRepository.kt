package com.example.shaloonapp.model.repository

import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.model.dto.User
import kotlinx.coroutines.flow.Flow


interface IRepository {

    //function for User Dao
    suspend fun getAllUser(): Flow<ResultState<List<User>>>
    suspend fun insertUser(user: User)
    suspend fun getUser(email:String,password:String):Flow<ResultState<User>>




    //function for Service Dao
    suspend fun getAllService(): Flow<ResultState<List<Service>>>
    suspend fun insertMultipleService(services: List<Service>)


    //function for Barber Dao
    suspend fun insertMultipleBarber(barber: List<Barber>)
    suspend fun getAllBarbers():Flow<ResultState<List<Barber>>>

}
