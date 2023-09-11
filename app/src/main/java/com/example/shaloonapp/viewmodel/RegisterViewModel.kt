package com.example.shaloonapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shaloonapp.model.dto.User
import com.example.shaloonapp.model.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val iRepository: IRepository):ViewModel() {

    suspend fun insertUser(user: User) = iRepository.insertUser(user)
}