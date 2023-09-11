package com.example.shaloonapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.model.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val iRepository: IRepository) : ViewModel() {

    private var _listOfBarber = MutableStateFlow<List<Barber>>(ArrayList<Barber>())
     val listOfBarber =_listOfBarber.asStateFlow()

    private var _errorResponse = MutableStateFlow<String>("")
    val errorResponse = _errorResponse.asStateFlow()

    init {
        getAllBarber()
    }


    fun getAllBarber(){
        viewModelScope.launch {
            iRepository.getAllBarbers().collectLatest {resultState ->
                when(resultState){
                    is ResultState.Success ->
                        resultState.body?.let {
                            _listOfBarber.value = it
                        }
                        is ResultState.Error ->{
                            _errorResponse.value = resultState.errorMessage
                        }

                    else -> {}
                }


            }
        }
    }




//
//    fun getListOfBarber()= listOf(
//        Barber(firstName = "xyz", lastName = "xyz",experience ="5Years",rating=3.0,imgUrL =""),
//        Barber(firstName = "xyz", lastName = "xyz",experience ="4Years",rating=2.0,imgUrL =""),
//        Barber(firstName = "xyz", lastName = "xyz",experience ="3Years",rating=5.0,imgUrL =""),
//        Barber(firstName = "xyz", lastName = "xyz",experience ="2Years",rating=3.8,imgUrL =""),
//        Barber(firstName = "xyz", lastName = "xyz",experience ="1Years",rating=4.3,imgUrL =""),
//        Barber(firstName = "xyz", lastName = "xyz",experience ="5Years",rating=3.0,imgUrL =""),
//        Barber(firstName = "xyz", lastName = "xyz",experience ="5Years",rating=4.0,imgUrL =""),
//        Barber(firstName = "xyz", lastName = "xyz",experience ="4Years",rating=3.0,imgUrL =""),
//
//    )
}