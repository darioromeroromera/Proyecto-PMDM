package com.example.proyectopmdm.data.models.network.responses.contact

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DeleteContactResponse(
    @SerializedName("result")
    @Expose
    val result: String,

    @SerializedName("details")
    @Expose
    val details: String
) {
}