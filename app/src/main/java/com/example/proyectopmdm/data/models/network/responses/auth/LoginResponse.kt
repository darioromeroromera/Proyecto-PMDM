package com.example.proyectopmdm.data.models.network.responses.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse() {
    @SerializedName("result")
    @Expose
    var result: String = ""

    @SerializedName("token")
    @Expose
    var token: String = ""

    @SerializedName("id")
    @Expose
    var id: String = ""

    @SerializedName("nombre")
    @Expose
    var name: String = ""

    @SerializedName("email")
    @Expose
    var email: String = ""

    @SerializedName("imagen")
    @Expose
    var imagen: String = ""

    @SerializedName("details")
    @Expose
    var details: String = ""
}