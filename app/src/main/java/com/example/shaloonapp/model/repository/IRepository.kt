package com.example.shaloonapp.model.repository


import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.model.dto.User

import kotlinx.coroutines.flow.Flow

interface IRepository {

    //function for User Dao
    suspend fun getAllUser(): Flow<ResultState<List<User>>>


    //function for Service Dao
    suspend fun getAllService(): Flow<ResultState<List<Service>>>

}
