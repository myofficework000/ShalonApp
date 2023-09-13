package com.example.shaloonapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shaloonapp.model.dto.AppointmentWithListOfService
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.model.dto.Service

class PostLoginSharedViewModel: ViewModel() {

    var currentAppointment = MutableLiveData<AppointmentWithListOfService>()
    var currentService = MutableLiveData<Service>()
    var currentBarber = MutableLiveData<Barber>()


}