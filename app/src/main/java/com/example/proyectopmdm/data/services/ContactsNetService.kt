package com.example.proyectopmdm.data.services

import com.example.proyectopmdm.data.models.network.requests.LoginRequest
import com.example.proyectopmdm.data.models.network.requests.RegisterRequest
import com.example.proyectopmdm.data.models.network.responses.LoginResponse
import com.example.proyectopmdm.data.models.network.responses.RegisterResponse
import retrofit2.Response
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactsNetService @Inject constructor(val apiService: ContactsNetServiceInterface){

    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        try {
            val response: Response<LoginResponse> = apiService.login(loginRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.success(it)
                } ?: return Result.failure(RuntimeException("Respuesta nula sin datos"))
            } else {
                return Result.failure(RuntimeException("Error en la llamada y sin respuesta"))
            }
        } catch(e: Exception){
            return Result.failure(e)
        }
    }


    suspend fun register(registerRequest: RegisterRequest): Result<RegisterResponse> {
        try {
            val response  = apiService.register(registerRequest)
            return if (response.isSuccessful){
                response.body()?.let {
                    Result.success(it)
                }?:Result.failure(RuntimeException("Respuesta nula"))
            } else {
                Result.failure(RuntimeException("Error en la llamada y sin respuesta"))
            }
        }
        catch (e: Exception) {
            return Result.failure(e)
        }

    }
}