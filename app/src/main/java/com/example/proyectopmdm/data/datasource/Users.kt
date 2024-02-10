package com.example.proyectopmdm.data.datasource

import com.example.proyectopmdm.data.models.User


object Users {
    val usersList: MutableList<User> = listOf(User("Dario", "1234", "dario@gmail.com")).toMutableList()
}