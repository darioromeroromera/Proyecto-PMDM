package com.example.proyectopmdm.domain.usecases.models

class EditContactData(
    val imagen: String?,
    val details: String
) {
    constructor(
        details: String
    ) : this(
        imagen = null,
        details
    )
}