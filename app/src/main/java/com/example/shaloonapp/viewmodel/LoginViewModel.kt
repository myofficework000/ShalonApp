package com.example.shaloonapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shaloonapp.model.ResultState
import com.example.shaloonapp.model.dto.User
import com.example.shaloonapp.model.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val iRepository: IRepository):ViewModel() {

    private var _user = MutableStateFlow<ResultState<User>>(ResultState.Loading())
    val user = _user.asStateFlow()



    fun getUser(email:String, password:String){
        viewModelScope.launch {
            iRepository.getUser(email,password).collectLatest{
                _user.value = it
            }
        }
    }
}