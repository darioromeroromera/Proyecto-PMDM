package com.example.proyectopmdm.data.models.network.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginRequest (
    @SerializedName("nombre")
    @Expose
    val name: String,

    @SerializedName("password")
    @Expose
    val password: String
){
}