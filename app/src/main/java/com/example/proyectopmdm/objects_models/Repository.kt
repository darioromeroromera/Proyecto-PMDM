package com.example.proyectopmdm.objects_models

import com.example.proyectopmdm.models.Contacto

object Repository {
    val listaContactos: List<Contacto> = listOf(
        Contacto(
            "Pepe Trabajo", "José Fernández Montañer", "647 28 19 39",
            "https://t4.ftcdn.net/jpg/03/49/49/79/360_F_349497933_Ly4im8BDmHLaLzgyKg2f2yZOvJjBtlw5.webp"
        ),
        Contacto(
            "Juli", "Julián Castañeda Orezuela", "612 37 51 82",
            "https://t4.ftcdn.net/jpg/03/49/49/79/360_F_349497933_Ly4im8BDmHLaLzgyKg2f2yZOvJjBtlw5.webp"
        ),
        Contacto(
            "Tía Lucía", "Lucía Hoyos Fuentemendábar", "621 20 12 77",
            "https://t4.ftcdn.net/jpg/03/49/49/79/360_F_349497933_Ly4im8BDmHLaLzgyKg2f2yZOvJjBtlw5.webp"
        )
    )
}