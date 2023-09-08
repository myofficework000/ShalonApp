package com.example.shaloonapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shaloonapp.model.dto.Service
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SelectServiceScreenViewModel
        @Inject constructor()
    : ViewModel() {

        var listOfService = MutableLiveData<List<Service>>()

        init {
            listOfService.postValue(getListOfService())
        }


    fun getListOfService()= listOf(
        Service(0,"hair cut 1",20,"haiadsdfas",10,""),
        Service(1,"hair cut 2",20,"haiadsdfas",10,""),
        Service(2,"hair cut 3",20,"haiadsdfas",10,""),
        Service(3,"hair cut 2",20,"haiadsdfas",10,""),
        Service(4,"hair cut 3",20,"haiadsdfas",10,""),
        Service(5,"hair cut 3",20,"haiadsdfas",10,""),
        )
}