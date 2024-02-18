package com.example.proyectopmdm.ui.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.domain.usecases.GetUserNameOnlyUseCase
import com.example.proyectopmdm.domain.usecases.GetUserUseCase
import com.example.proyectopmdm.domain.usecases.SaveUserUseCase
import com.example.proyectopmdm.domain.usecases.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserNameOnlyUseCase: GetUserNameOnlyUseCase
) : ViewModel(){
    val userLiveData = MutableLiveData<UserModel>()

    fun getUser(name: String, password: String) : UserModel? {
        var user : UserModel? = null
        viewModelScope.launch {
            user = getUserUseCase(name, password)
            user?.let {
                userLiveData.value = user!!
            }
        }
        return user
    }

    fun getUserByName(name: String) : UserModel? {
        var user : UserModel? = null
        viewModelScope.launch {
            user = getUserNameOnlyUseCase(name)
            user?.let {
                userLiveData.value = user!!
            }
        }
        return user
    }

    fun saveUser(userModel: UserModel) {
        viewModelScope.launch {
            saveUserUseCase(userModel)
        }
    }
}