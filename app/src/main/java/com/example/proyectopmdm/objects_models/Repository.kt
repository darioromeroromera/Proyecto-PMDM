package com.example.proyectopmdm.objects_models

import com.example.proyectopmdm.models.Contacto

object Repository {
    val listaContactos: List<Contacto> = listOf(
        Contacto(
            "Pepe Trabajo", "José Fernández Montañer", "647 28 19 39",
            "https://img.freepik.com/free-vector/illustration-businessman_53876-5856.jpg"
        ),
        Contacto(
            "Juli", "Julián Castañeda Orezuela", "612 37 51 82",
            "https://img.freepik.com/free-vector/illustration-businessman_53876-5856.jpg"
        ),
        Contacto(
            "Tía Lucía", "Lucía Hoyos Fuentes", "621 20 12 77",
            "https://img.freepik.com/free-vector/illustration-businessman_53876-5856.jpg"
        )
    )
}