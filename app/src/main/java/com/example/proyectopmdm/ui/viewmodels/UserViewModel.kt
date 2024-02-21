package com.example.proyectopmdm.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectopmdm.domain.usecases.users.GetUserNameOnlyUseCase
import com.example.proyectopmdm.domain.usecases.users.GetUserUseCase
import com.example.proyectopmdm.domain.usecases.users.SaveUserUseCase
import com.example.proyectopmdm.domain.usecases.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserNameOnlyUseCase: GetUserNameOnlyUseCase
) : ViewModel(){
    val userLiveData = MutableLiveData<UserModel?>()

    suspend fun getUser(name: String, password: String) {
        val user = getUserUseCase(name, password)
        userLiveData.value = user
    }

    suspend fun getUserByName(name: String) {
        val user = getUserNameOnlyUseCase(name)
        userLiveData.value = user
    }

    suspend fun saveUser(userModel: UserModel) {
        saveUserUseCase(userModel)
    }
}