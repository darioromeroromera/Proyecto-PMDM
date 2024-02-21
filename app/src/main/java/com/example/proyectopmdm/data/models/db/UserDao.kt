package com.example.proyectopmdm.data.models.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectopmdm.data.models.db.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE name = :name AND password = :password") // Para el login
    suspend fun getUser(name: String, password: String) : UserEntity

    @Query("SELECT * FROM users WHERE name = :name") // Para el registro (ver si ya existen usuarios con el mismo username)
    suspend fun getUserByUsername(name: String) : UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(vararg user: UserEntity)
}