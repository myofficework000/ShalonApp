package com.example.shaloonapp.model.repository

import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dto.Appointment
import com.example.shaloonapp.model.dto.AppointmentWithListOfService
import com.example.shaloonapp.model.dto.AppointmentWithServiceCrossRef
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.model.dto.User
import kotlinx.coroutines.flow.Flow


interface IRepository {

    //function for User Dao
    suspend fun getAllUser(): Flow<ResultState<List<User>>>
    suspend fun getUser(userId:Int):Flow<ResultState<User>>
    suspend fun getUser(emailId:String, password: String):Flow<ResultState<User>>
    suspend fun insertUser(user: User)
    suspend fun insertMultipleUser(users: List<User>)

    //function for Service Dao
    suspend fun getAllService(): Flow<ResultState<List<Service>>>
    suspend fun insertMultipleService(services: List<Service>)

    suspend fun getServiceById(serviceId: Int): Flow<ResultState<Service>>
    suspend fun insertService(service: Service)

    //function for Barber Dao

    suspend fun insertBarber(barber: Barber)
    suspend fun insertMultipleBarber(barber: List<Barber>)

    suspend fun getAllBarber(): Flow<ResultState<List<Barber>>>
    suspend fun getBarberById(barberId: Int): Flow<ResultState<Barber>>

    //function for Appointment Dao
    suspend fun getAllAppointment(): Flow<ResultState<List<Appointment>>>
    suspend fun getAppointmentById(appointmentId: Int): Flow<ResultState<Appointment>>
    suspend fun getAppointmentByUserId(userId: Int): Flow<ResultState<List<Appointment>>>
    suspend fun insertAppointment(appointment: Appointment)
    suspend fun insertMultipleAppointment(appointments: List<Appointment>)
    suspend fun getAllAppointmentWithListOfService(): Flow<ResultState<List<AppointmentWithListOfService>>>
    suspend fun getAppointmentWithListOfServiceByUserId(userId: Int): Flow<ResultState<List<AppointmentWithListOfService>>>

    //function for AppointmentWithServiceCrossRef Dao-------------------------------------------
    suspend fun insertAppointmentWithServiceCrossRef(appointmentWithServiceCrossRef: AppointmentWithServiceCrossRef)
    suspend fun getAllAppointmentWithServiceCrossRef(): Flow<ResultState<List<AppointmentWithServiceCrossRef>>>
    suspend fun insertMultipleAppointmentWithServiceCrossRef(appointmentWithServiceCrossRefs: List<AppointmentWithServiceCrossRef>)
}
