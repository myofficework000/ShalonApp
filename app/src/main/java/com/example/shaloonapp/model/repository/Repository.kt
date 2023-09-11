package com.example.shaloonapp.model.repository

import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dao.AppointmentDao
import com.example.shaloonapp.model.dao.AppointmentWithListOfServiceDao
import com.example.shaloonapp.model.dao.AppointmentWithServiceCrossRefDao
import com.example.shaloonapp.model.dao.BarberDao
import com.example.shaloonapp.model.dao.ServiceDao
import com.example.shaloonapp.model.dao.UserDao
import com.example.shaloonapp.model.dto.Appointment
import com.example.shaloonapp.model.dto.AppointmentWithListOfService
import com.example.shaloonapp.model.dto.AppointmentWithServiceCrossRef
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
        private val barberDao: BarberDao,
        private val appointmentWithServiceCrossRefDao: AppointmentWithServiceCrossRefDao,
        private val appointmentDao: AppointmentDao,
        private val appointmentWithListOfServiceDao: AppointmentWithListOfServiceDao

    ): IRepository {

    //function for User Dao------------------------------------------------------------------------
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

    override suspend fun getUser(userId: Int): Flow<ResultState<User>> {
        return flow {
            userDao.getUser(userId).apply {
                this.let {
                    emit(ResultState.Success(it))
                }.runCatching {
                    // error
                }
            }
        }
    }

    override suspend fun insertUser(user: User) { userDao.insertUser(user) }
    override suspend fun insertMultipleUser(users: List<User>) {
        userDao.insertMultipleService(users)
    }


    //function for Service Dao---------------------------------------------------------------------
    override suspend fun getAllService()
    : Flow<ResultState<List<Service>>> {
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

    override suspend fun getServiceById(serviceId: Int)
    : Flow<ResultState<Service>> {
        return flow {
            serviceDao.getServiceById(serviceId).apply {
                emit(ResultState.Success(this))
                    .runCatching {
                        // error
                    }
            }
        }
    }
    override suspend fun insertService(service: Service)  {
        serviceDao.insertService(service)
    }

    //function for Barber Dao----------------------------------------------------------------------

    override suspend fun insertBarber(barber: Barber) { barberDao.insertBarber(barber) }

    override suspend fun insertMultipleBarber(barbers: List<Barber>) {
        barberDao.insertMultipleBarber(barbers)
    }
    override suspend fun getAllBarber()
    : Flow<ResultState<List<Barber>>> {
        return flow {
            barberDao.getAllBarber().apply {
                emit(ResultState.Success(this))
                    .runCatching {
                        // error
                    }
            }
        }
    }
    override suspend fun getBarberById(barberId:Int)
    : Flow<ResultState<Barber>> {
        return flow {
            barberDao.getBarberById(barberId).apply {
                emit(ResultState.Success(this))
                    .runCatching {
                        // error
                    }
            }
        }
    }

    //function for Appointment Dao------------------------------------------------------------------

    override suspend fun getAllAppointment()
    : Flow<ResultState<List<Appointment>>> {
        return flow {
            appointmentDao.getAllAppointment().apply {
                emit(ResultState.Success(this))
                    .runCatching {
                        // error
                    }
            }
        }
    }

    override suspend fun getAppointmentById(appointmentId: Int)
    : Flow<ResultState<Appointment>> {
        return flow {
            appointmentDao.getAppointmentById(appointmentId).apply {
                emit(ResultState.Success(this))
                    .runCatching {
                        // error
                    }
            }
        }
    }

    override suspend fun getAppointmentByUserId(userId: Int)
    : Flow<ResultState<List<Appointment>>> {
        return flow {
            appointmentDao.getAppointmentByUserId(userId).apply {
                emit(ResultState.Success(this))
                    .runCatching {
                        // error
                    }
            }
        }
    }
    override suspend fun insertAppointment(appointment: Appointment) {
        appointmentDao.insertAppointment(appointment)
    }
    override suspend fun insertMultipleAppointment(appointments: List<Appointment>) {
        appointmentDao.insertMultipleAppointment(appointments)
    }

    //function for appointmentWithListOfService Dao-------------------------------------------------
    override suspend fun getAllAppointmentWithListOfService()
            : Flow<ResultState<List<AppointmentWithListOfService>>> {
        return flow {
            appointmentWithListOfServiceDao
                .getAllAppointmentWithListOfService().apply {
                emit(ResultState.Success(this))
                    .runCatching {
                        // error
                    }
            }
        }
    }

    override suspend fun getAppointmentWithListOfServiceByUserId(userId: Int)
            : Flow<ResultState<List<AppointmentWithListOfService>>> {
        return flow {
            appointmentWithListOfServiceDao
                .getAppointmentWithListOfServiceByUserId(userId).apply {
                    emit(ResultState.Success(this))
                        .runCatching {
                            // error
                        }
                }
        }
    }


    //function for AppointmentWithServiceCrossRef Dao-------------------------------------------

    override suspend fun insertAppointmentWithServiceCrossRef(
        appointmentWithServiceCrossRef: AppointmentWithServiceCrossRef
    ) {
        appointmentWithServiceCrossRefDao
            .insertAppointmentWithServiceCrossRef(appointmentWithServiceCrossRef)
    }

    override suspend fun getAllAppointmentWithServiceCrossRef()
            : Flow<ResultState<List<AppointmentWithServiceCrossRef>>> {
        return flow {
            appointmentWithServiceCrossRefDao
                .getAllAppointmentWithServiceCrossRef().apply {
                    emit(ResultState.Success(this))
                        .runCatching {
                            // error
                        }
                }
        }
    }
    override suspend fun insertMultipleAppointmentWithServiceCrossRef(
        appointmentWithServiceCrossRefs: List<AppointmentWithServiceCrossRef>
    ) {
        appointmentWithServiceCrossRefDao
            .insertMultipleAppointmentWithServiceCrossRef(appointmentWithServiceCrossRefs)
    }



}