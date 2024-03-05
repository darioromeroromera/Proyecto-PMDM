package com.example.proyectopmdm.data.models.network.responses

class ContactoNet(
    val id: Long,
    val id_usuario: Long,
    val nombre: String,
    val nombre_completo: String,
    val telefono: String,
    val detalles: String,
    val imagen: String
) {

    constructor(
        id: Long,
        id_usuario: Long,
        nombre: String,
        nombre_completo: String,
        telefono: String,
        detalles: String
    ) : this(
        id,
        id_usuario,
        nombre,
        nombre_completo,
        telefono,
        detalles,
        imagen = ""
    ) {
    }
}