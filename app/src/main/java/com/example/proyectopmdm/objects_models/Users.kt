package com.example.proyectopmdm.objects_models

import com.example.proyectopmdm.models.User


object Users {
    val usersList: MutableList<User> = listOf(User("Dario", "1234")).toMutableList()
}