package com.example.shaloonapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dto.Appointment
import com.example.shaloonapp.model.dto.AppointmentWithListOfService
import com.example.shaloonapp.model.dto.AppointmentWithServiceCrossRef
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.model.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostLoginSharedViewModel
@Inject constructor(private val iRepository: IRepository): ViewModel() {

    var currentAppointment = MutableLiveData<AppointmentWithListOfService>()
    var currentService = MutableLiveData<Service>()
    var currentBarber = MutableLiveData<Barber>()
    var currentDate = MutableLiveData<String>()
    var currentTime = MutableLiveData<String>()

    val appointmentInsertResultLiveData = MutableLiveData<Long>(0)


    fun insertAppointment(appointment: Appointment, currentService: Service){

        viewModelScope.launch {
            val appointmentInsertDeferred = async {
                iRepository.insertAppointment(appointment) }

            val appointmentInsertResult = appointmentInsertDeferred.await()

            if(appointmentInsertResult !=-1L){
                iRepository.insertMultipleAppointmentWithServiceCrossRef(
                    generateAppointmentServiceCrossRef(appointmentInsertResult, listOf(currentService)))

            }
            appointmentInsertResultLiveData.postValue(appointmentInsertResult)
            getAppointmentById(appointmentInsertResultLiveData.value!!)
        }

    }
    fun generateAppointmentServiceCrossRef(
        appointmentId: Long,
        listOfService: List<Service>): List<AppointmentWithServiceCrossRef>{

        val list = ArrayList<AppointmentWithServiceCrossRef>()
        for( service in listOfService)
            list.add(AppointmentWithServiceCrossRef(appointmentId,service.serviceId))
        return list
    }
    fun getAppointmentById(appointmentId: Long){
        viewModelScope.launch {
            iRepository.getAllAppointmentWithListOfServiceById(appointmentId).collectLatest { resultState ->
                when(resultState){
                    is ResultState.Success ->
                        resultState.body?.let {
                            currentAppointment.value = it
                        }
                    is ResultState.Error ->{

                    }
                    else ->{

                    }
                }

            }
        }
    }
}