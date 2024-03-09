package com.example.proyectopmdm.data.services

import com.example.proyectopmdm.data.models.network.requests.AddPutContactRequest
import com.example.proyectopmdm.data.models.network.requests.LoginRequest
import com.example.proyectopmdm.data.models.network.requests.RegisterRequest
import com.example.proyectopmdm.data.models.network.responses.contact.AddContactResponse
import com.example.proyectopmdm.data.models.network.responses.contact.GetContactsResponse
import com.example.proyectopmdm.data.models.network.responses.auth.LoginResponse
import com.example.proyectopmdm.data.models.network.responses.auth.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ContactsNetServiceInterface {
    @POST("registro")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("auth")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<LoginResponse>

    @GET("contacto")
    suspend fun getContacts(@Header(value = "api-key") token: String) : Response<GetContactsResponse>

    @POST("contacto")
    suspend fun addContact(@Header(value = "api-key") token: String, @Body addPutContactRequest: AddPutContactRequest) : Response<AddContactResponse>
}