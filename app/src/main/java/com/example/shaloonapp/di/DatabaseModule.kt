package com.example.shaloonapp.di

import android.content.Context
import com.example.shaloonapp.model.AppDatabase
import com.example.shaloonapp.model.dao.AppointmentDao
import com.example.shaloonapp.model.dao.AppointmentWithListOfServiceDao
import com.example.shaloonapp.model.dao.AppointmentWithServiceCrossRefDao
import com.example.shaloonapp.model.dao.BarberDao
import com.example.shaloonapp.model.dao.ServiceDao
import com.example.shaloonapp.model.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getAppDatabase(context) ?: throw IllegalStateException("AppDatabase not initialized.")
    }
    @Provides
    @Singleton
    fun getUserDao(appDatabase: AppDatabase): UserDao{
        return appDatabase.getUserDao()
    }

    @Provides
    @Singleton
    fun getServiceDao(appDatabase: AppDatabase): ServiceDao{
        return appDatabase.getServiceDao()
    }

    @Provides
    @Singleton
    fun getBarberDao(appDatabase: AppDatabase): BarberDao{
        return appDatabase.getBarberDao()
    }

    @Provides
    @Singleton
    fun getAppointmentDao(appDatabase: AppDatabase): AppointmentDao{
        return appDatabase.getAppointmentDao()
    }
    @Provides
    @Singleton
    fun getAppointmentWithListOfServiceDao(appDatabase: AppDatabase): AppointmentWithListOfServiceDao {
        return appDatabase.getAppointmentWithListOfServiceDao()
    }
    @Provides
    @Singleton
    fun getAppointmentWithServiceCrossRefDao(appDatabase: AppDatabase): AppointmentWithServiceCrossRefDao {
        return appDatabase.getAppointmentWithServiceCrossRefDao()
    }
}