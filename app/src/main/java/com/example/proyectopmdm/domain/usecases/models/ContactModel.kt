package com.example.proyectopmdm.domain.usecases.models

class ContactModel(
    val id: Long,
    val idUsuario: Long,
    val nombre: String,
    val nombreCompleto: String,
    val telefono: String,
    val detalles: String,
    val imagen: String
) {

    constructor(
        id: Long,
        idUsuario: Long,
        nombre: String,
        nombreCompleto: String,
        telefono: String,
        detalles: String
    ) : this(
        id,
        idUsuario,
        nombre,
        nombreCompleto,
        telefono,
        detalles,
        imagen = ""
    ) {
    }
}