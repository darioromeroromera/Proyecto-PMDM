package com.example.proyectopmdm.data.models

class Contacto (
    var nombre: String,
    var nombreCompleto: String,
    var telefono: String,
    var imagen: String,
    var detalles: String
){
    override fun toString(): String {
        return String.format("Nombre: %s\nNombre completo: %s\nTeléfono: %s", nombre, nombreCompleto, telefono)
    }
}