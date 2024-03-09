package com.example.proyectopmdm.data.models.network.responses.contact

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PutContactResponse(
    @SerializedName("file_img")
    @Expose
    val imagen: String,

    @SerializedName("details")
    @Expose
    val details: String
) {
}