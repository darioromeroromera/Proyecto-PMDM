package com.example.proyectopmdm.domain.usecases.models

import android.icu.text.PluralRules

class UserModel (
    val name: String,
    val password: String,
    val email: String,
    val token: String,
    val details: String
){
    constructor(
        details: String
    ) : this(
        name = "",
        password = "",
        email = "",
        token = "",
        details
    )

    constructor(
        name: String,
        password: String,
        email: String,
    ) : this(
        name,
        password,
        email,
        token = "",
        details = ""
    )

    constructor(
        name: String,
        password: String,
    ) : this(
        name,
        password,
        email = "",
        token = "",
        details = ""
    )
}