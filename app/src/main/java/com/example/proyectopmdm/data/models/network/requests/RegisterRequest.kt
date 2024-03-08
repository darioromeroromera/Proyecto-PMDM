package com.example.proyectopmdm.data.models.network.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterRequest(
    @SerializedName("email")
    @Expose
    val email: String,

    @SerializedName("nombre")
    @Expose
    val name: String,

    @SerializedName("password")
    @Expose
    val password: String,

    @SerializedName("imagen")
    @Expose
    val image: String?
) {
    constructor(
        email: String,
        name: String,
        password: String
    ) : this(
        email,
        name,
        password,
        image = null
    )

    @SerializedName("disponible")
    @Expose
    val disponible: String = "1"
}