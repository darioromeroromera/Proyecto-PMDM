package com.example.proyectopmdm.data.models.mem

class MutableRepository {
    companion object {
        var contacts:MutableList<Contacto> = emptyList<Contacto>().toMutableList()
    }
}