package com.example.shaloonapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shaloonapp.model.dto.Appointment
import com.example.shaloonapp.model.dto.Barber

class PostLoginSharedViewModel: ViewModel() {

    var currentAppointment = MutableLiveData<Appointment>()
    var currentBarber = MutableLiveData<Barber>()
}