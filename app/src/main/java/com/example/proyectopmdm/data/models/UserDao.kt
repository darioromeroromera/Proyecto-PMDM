package com.example.proyectopmdm.data.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectopmdm.domain.usecases.models.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE name = :name AND password = :password")
    suspend fun getUser(name: String, password: String) : UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(name: String, password: String, email: String)
}