package com.example.shaloonapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dto.Appointment
import com.example.shaloonapp.model.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAppointmentScreenViewModel
    @Inject constructor(
        private val iRepository: IRepository
    )
    : ViewModel() {

    private var _listOfAppointment = MutableStateFlow<List<Appointment>>(ArrayList<Appointment>())
    val listOfAppointment = _listOfAppointment.asStateFlow()

    private var _errorResponse = MutableStateFlow<String>("")
    val errorResponse = _errorResponse.asStateFlow()

    init {
       // getAllAppointment()
        getAppointmentByUserId(1)
    }
    private fun getAllAppointment(){
        viewModelScope.launch {
            iRepository.getAllAppointment().collectLatest { resultState ->
                when(resultState){
                    is ResultState.Success ->
                        resultState.body?.let {
                            _listOfAppointment.value = it
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
    private fun getAppointmentByUserId(userId: Int){
        viewModelScope.launch {
            iRepository.getAppointmentByUserId(userId).collectLatest { resultState ->
                when(resultState){
                    is ResultState.Success ->
                        resultState.body?.let {
                            _listOfAppointment.value = it
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
}