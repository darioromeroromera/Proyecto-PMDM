package com.example.proyectopmdm.domain.usecases.models

class ContactModel(
    var id: Long?,
    val nombre: String,
    val nombreCompleto: String,
    val telefono: String,
    val detalles: String,
    var imagen: String?
) {

    constructor(
        id: Long,
        nombre: String,
        nombreCompleto: String,
        telefono: String,
        detalles: String
    ) : this(
        id,
        nombre,
        nombreCompleto,
        telefono,
        detalles,
        imagen = null
    ) {
    }

    constructor(
        nombre: String,
        nombreCompleto: String,
        telefono: String,
        detalles: String
    ) : this(
        id = null,
        nombre,
        nombreCompleto,
        telefono,
        detalles,
        imagen = null
    ) {
    }

    constructor(
        nombre: String,
        nombreCompleto: String,
        telefono: String,
        detalles: String,
        imagen: String?
    ) : this(
        id = null,
        nombre,
        nombreCompleto,
        telefono,
        detalles,
        imagen
    ) {
    }
}