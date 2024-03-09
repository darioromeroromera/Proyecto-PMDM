package com.example.proyectopmdm.domain.usecases.models

import android.icu.text.PluralRules

class UserModel (
    val name: String,
    val password: String,
    val email: String,
    val token: String,
    val details: String,
    val imagen: String?
){
    // Constructor para detalles
    constructor(
        details: String
    ) : this(
        name = "",
        password = "",
        email = "",
        token = "",
        details,
        imagen = ""
    )

    // Constructor para usuario sin imagen

    constructor(
        name: String,
        password: String,
        email: String,
        token: String,
        details: String
    ) : this(
        name,
        password,
        email,
        token,
        details,
        imagen = null
    )

    // Constructor para registro

    constructor(
        name: String,
        password: String,
        email: String,
        imagen: String?
    ) : this(
        name,
        password,
        email,
        token = "",
        details = "",
        imagen
    )

    // Constructor para login

    constructor(
        name: String,
        password: String,
    ) : this(
        name,
        password,
        email = "",
        token = "",
        details = "",
        imagen = ""
    )
}