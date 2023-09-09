package com.example.shaloonapp.viewmodel

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
}