package com.example.shaloonapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.model.dto.Service
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    var listOfBarber = MutableLiveData<List<Barber>>()

    init {
        listOfBarber.postValue(getListOfBarber())
    }


    fun getListOfBarber()= listOf(
        Barber(firstName = "xyz", lastName = "xyz",experience ="5Years",rating=3.0,imgUrL =""),
        Barber(firstName = "xyz", lastName = "xyz",experience ="4Years",rating=2.0,imgUrL =""),
        Barber(firstName = "xyz", lastName = "xyz",experience ="3Years",rating=5.0,imgUrL =""),
        Barber(firstName = "xyz", lastName = "xyz",experience ="2Years",rating=3.8,imgUrL =""),
        Barber(firstName = "xyz", lastName = "xyz",experience ="1Years",rating=4.3,imgUrL =""),
        Barber(firstName = "xyz", lastName = "xyz",experience ="5Years",rating=3.0,imgUrL =""),
        Barber(firstName = "xyz", lastName = "xyz",experience ="5Years",rating=4.0,imgUrL =""),
        Barber(firstName = "xyz", lastName = "xyz",experience ="4Years",rating=3.0,imgUrL =""),

    )
}