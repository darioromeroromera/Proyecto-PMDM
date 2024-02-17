package com.example.proyectopmdm.framework

import android.content.Context
import androidx.room.Room
import com.example.proyectopmdm.data.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
object RoomModule {
    private const val NAME_DATABASE = "db_users"
    @Singleton
    @Provides
    fun provideRoom( @ApplicationContext context: Context) =
        Room.databaseBuilder(context, UserDatabase:: class.java, NAME_DATABASE).build()
    @Singleton
    @Provides
    fun provideDao(database: UserDatabase) = database.userDao()
}