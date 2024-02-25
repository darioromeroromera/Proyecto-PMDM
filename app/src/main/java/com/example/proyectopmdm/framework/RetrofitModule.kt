package com.example.proyectopmdm.framework

import com.example.proyectopmdm.data.services.ContactsNetServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule{
    private const val URL_BASE_RETROFIT = "http://192.168.1.134/api-pueblos/endp/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl(URL_BASE_RETROFIT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Singleton
    @Provides
    fun provideServiceApi(retrofit : Retrofit): ContactsNetServiceInterface =
        retrofit
            .create(ContactsNetServiceInterface::class.java)
}