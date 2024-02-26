package com.example.proyectopmdm.data.models.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterResponse {
    @SerializedName("result")
    @Expose
    var result: String = ""

    @SerializedName("insert_id")
    @Expose
    var insertId: String = ""

    @SerializedName("details")
    @Expose
    var details: String = ""
}