package com.example.proyectopmdm.data.services

import com.example.proyectopmdm.data.models.network.requests.AddPutContactRequest
import com.example.proyectopmdm.data.models.network.requests.LoginRequest
import com.example.proyectopmdm.data.models.network.requests.RegisterRequest
import com.example.proyectopmdm.data.models.network.responses.contact.AddContactResponse
import com.example.proyectopmdm.data.models.network.responses.contact.GetContactsResponse
import com.example.proyectopmdm.data.models.network.responses.auth.LoginResponse
import com.example.proyectopmdm.data.models.network.responses.auth.RegisterResponse
import com.example.proyectopmdm.data.models.network.responses.contact.DeleteContactResponse
import com.example.proyectopmdm.data.models.network.responses.contact.PutContactResponse
import org.json.JSONObject
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
                response.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    return Result.failure(RuntimeException(jsonObj.getString("details")))
                } ?: return Result.failure(RuntimeException("Respuesta nula sin datos"))
            }
        } catch(e: Exception){
            return Result.failure(e)
        }
    }


    suspend fun register(registerRequest: RegisterRequest): Result<RegisterResponse> {
        try {
            val response: Response<RegisterResponse> = apiService.register(registerRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.success(it)
                } ?: return Result.failure(RuntimeException("Respuesta nula sin datos"))
            } else {
                response.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    return Result.failure(RuntimeException(jsonObj.getString("details")))
                } ?: return Result.failure(RuntimeException("Respuesta nula sin datos"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun getContacts(token: String): Result<GetContactsResponse> {
        try {
            val response: Response<GetContactsResponse> = apiService.getContacts(token)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.success(it)
                } ?: return Result.failure(RuntimeException("Respuesta nula sin datos"))
            } else {
                response.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    return Result.failure(RuntimeException(jsonObj.getString("details")))
                } ?: return Result.failure(RuntimeException("Respuesta nula sin datos"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun addContact(token: String, addPutContactRequest: AddPutContactRequest): Result<AddContactResponse> {
        try {
            val response: Response<AddContactResponse> = apiService.addContact(token, addPutContactRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.success(it)
                } ?: return Result.failure(RuntimeException("Respuesta nula sin datos"))
            } else {
                response.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    return Result.failure(RuntimeException(jsonObj.getString("details")))
                } ?: return Result.failure(RuntimeException("Respuesta nula sin datos"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun deleteContact(token: String, id : Long): Result<DeleteContactResponse> {
        try {
            val response: Response<DeleteContactResponse> = apiService.deleteContact(token, id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.success(it)
                } ?: return Result.failure(RuntimeException("Respuesta nula sin datos"))
            } else {
                response.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    return Result.failure(RuntimeException(jsonObj.getString("details")))
                } ?: return Result.failure(RuntimeException("Respuesta nula sin datos"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun updateContact(token: String, addPutContactRequest: AddPutContactRequest, id : Long): Result<PutContactResponse> {
        try {
            val response: Response<PutContactResponse> = apiService.updateContact(token, addPutContactRequest, id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.success(it)
                } ?: return Result.failure(RuntimeException("Respuesta nula sin datos"))
            } else {
                response.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    return Result.failure(RuntimeException(jsonObj.getString("details")))
                } ?: return Result.failure(RuntimeException("Respuesta nula sin datos"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}