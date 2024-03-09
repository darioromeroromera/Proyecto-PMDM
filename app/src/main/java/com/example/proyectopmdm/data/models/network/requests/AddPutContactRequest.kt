package com.example.proyectopmdm.data.models.network.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddPutContactRequest(
    @SerializedName("nombre")
    @Expose
    val nombre: String,

    @SerializedName("nombre_completo")
    @Expose
    val nombreCompleto: String,

    @SerializedName("telefono")
    @Expose
    val telefono: String,

    @SerializedName("detalles")
    @Expose
    val detalles: String,

    @SerializedName("imagen")
    @Expose
    val imagen: String?
) {
    constructor(
        nombre: String,
        nombreCompleto: String,
        telefono: String,
        detalles: String
    ) : this(
        nombre,
        nombreCompleto,
        telefono,
        detalles,
        imagen = null
    )
}