package com.example.proyectopmdm.domain.usecases.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "email") val email: String?
){
}