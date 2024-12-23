package com.example.proyectopmdm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectopmdm.data.models.db.UserDao
import com.example.proyectopmdm.data.models.db.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}