package com.example.shaloonapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shaloonapp.model.dto.AppointmentWithListOfService
import com.example.shaloonapp.model.dto.Barber

class PostLoginSharedViewModel: ViewModel() {

    var currentAppointment = MutableLiveData<AppointmentWithListOfService>()
    var currentBarber = MutableLiveData<Barber>()


}