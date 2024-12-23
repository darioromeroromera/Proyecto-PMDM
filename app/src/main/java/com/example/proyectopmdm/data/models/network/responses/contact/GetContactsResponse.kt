package com.example.proyectopmdm.data.models.network.responses.contact

import com.example.proyectopmdm.data.models.network.ContactoNet
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetContactsResponse {
    @SerializedName("contactos")
    @Expose
    var contactos: List<ContactoNet> = emptyList<ContactoNet>().toMutableList()

    @SerializedName("details")
    @Expose
    var details: String = ""
}