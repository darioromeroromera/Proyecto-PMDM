package com.example.proyectopmdm.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.domain.usecases.GetUserUseCase
import com.example.proyectopmdm.domain.usecases.SaveUserUseCase
import com.example.proyectopmdm.domain.usecases.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel(){
    val userLiveData = MutableLiveData<UserModel>()

    suspend fun getUser(name: String, password: String) {
        viewModelScope.launch {
            val user = getUserUseCase(name, password)
            userLiveData.value = user!!
        }
    }

    suspend fun saveUser(name: String, password: String, email: String) {
        viewModelScope.launch {
            saveUserUseCase(name, password, email)
        }
    }
}