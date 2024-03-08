package com.example.proyectopmdm.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectopmdm.domain.usecases.models.UserModel
import com.example.proyectopmdm.domain.usecases.users.network.RegisterUseCase
import com.example.proyectopmdm.domain.usecases.users.network.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    //private val getUserUseCase: GetUserUseCase,
    /*private val saveUserUseCase: SaveUserUseCase,
    private val getUserNameOnlyUseCase: GetUserNameOnlyUseCase,*/
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel(){
    val userLiveData = MutableLiveData<UserModel?>()

    /*suspend fun getUser(name: String, password: String) {
        val user = getUserUseCase(name, password)
        userLiveData.value = user
    }*/

    suspend fun login(username: String, password: String) {
        val user = loginUseCase(username, password)
        userLiveData.value = user
    }

    suspend fun saveUser(userModel: UserModel) : String {
        return registerUseCase(userModel)
    }
}