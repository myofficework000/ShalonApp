package com.example.shaloonapp.model.repository

import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dao.BarberDao
import com.example.shaloonapp.model.dao.ServiceDao
import com.example.shaloonapp.model.dao.UserDao
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.model.dto.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository
    @Inject constructor(
        private val userDao: UserDao,
        private val serviceDao: ServiceDao,
        private val barberDao: BarberDao
    ): IRepository {
    override suspend fun getAllUser(): Flow<ResultState<List<User>>> {
        return flow {
            userDao.getAllUser().apply {
                this.let {
                    emit(ResultState.Success(it))
                }.runCatching {
                    // error
                }
            }
        }
    }

    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun getUser(email: String, password: String): Flow<ResultState<User>> {
        return flow {
            userDao.getUserForCredentials(email,password)?.let {
                emit(ResultState.Success(it))
            }?: emit(ResultState.Error("Invalid credentials"))

        }
    }

    override suspend fun getAllService(): Flow<ResultState<List<Service>>> {
        return flow {
            serviceDao.getAllService().apply {
                emit(ResultState.Success(this))
                    .runCatching {
                    // error
                }
            }
        }
    }
    override suspend fun insertMultipleService(services: List<Service>)  {
        serviceDao.insertMultipleService(services)
    }

    override suspend fun insertMultipleBarber(barbers: List<Barber>) {
        barberDao.insertMultipleBarber(barbers)
    }

    override suspend fun getAllBarbers(): Flow<ResultState<List<Barber>>> {
        return flow{
            barberDao.getAllBarber().apply{
                emit(ResultState.Success(this)).runCatching {
//                    error
                }
            }
        }
    }
}