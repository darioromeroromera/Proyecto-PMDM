package com.example.proyectopmdm.domain.usecases.models


class AddContactData(
    val insertId: Long,
    val imagen: String?,
    val details: String
) {
    constructor(
        details: String
    ) : this(
        insertId = 0,
        imagen = null,
        details
    )
}