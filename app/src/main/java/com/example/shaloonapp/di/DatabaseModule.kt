package com.example.shaloonapp.di

import com.example.shaloonapp.model.AppDatabase
import com.example.shaloonapp.model.dao.ServiceDao
import com.example.shaloonapp.model.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun getUserDao(appDatabase: AppDatabase): UserDao{
        return appDatabase.getUserDao()
    }

    @Provides
    fun getServiceDao(appDatabase: AppDatabase): ServiceDao{
        return appDatabase.getServiceDao()
    }
}