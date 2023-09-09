package com.example.shaloonapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.model.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SelectServiceScreenViewModel
        @Inject constructor(
            private val iRepository: IRepository
        )
    : ViewModel() {

        private var _listOfService = MutableStateFlow<List<Service>>(ArrayList<Service>())
        val listOfService = _listOfService.asStateFlow()

        private var _errorResponse = MutableStateFlow<String>("")
        val errorResponse = _errorResponse.asStateFlow()

    init {
        //insertMultipleService()
        getAllService()
    }
    fun getAllService(){
        viewModelScope.launch {
            iRepository.getAllService().collectLatest { resultState ->
                when(resultState){
                    is ResultState.Success ->
                        resultState.body?.let {
                            _listOfService.value = it
                        }
                    is ResultState.Error ->{
                        _errorResponse.value = resultState.errorMessage
                    }
                    else ->{

                    }
                }

            }
        }
    }
    fun insertMultipleService(){
        viewModelScope.launch {
            try {
                iRepository.insertMultipleService(getListOfService())
            }catch (e: Exception){
                Log.i("SelectServiceScreenViewModel", e.printStackTrace().toString())
            }
        }
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