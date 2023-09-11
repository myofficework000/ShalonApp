package com.example.shaloonapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shaloonapp.model.dao.AppointmentDao
import com.example.shaloonapp.model.dao.AppointmentWithListOfServiceDao
import com.example.shaloonapp.model.dao.AppointmentWithServiceCrossRefDao
import com.example.shaloonapp.model.dao.BarberDao
import com.example.shaloonapp.model.dao.ServiceDao
import com.example.shaloonapp.model.dao.UserDao
import com.example.shaloonapp.model.dto.Appointment
import com.example.shaloonapp.model.dto.AppointmentWithServiceCrossRef
import com.example.shaloonapp.model.dto.Barber
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.model.dto.User

@Database(entities =
    [
        User::class, Service::class, Barber::class,
        Appointment::class, AppointmentWithServiceCrossRef::class
    ],
    version = 3, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getServiceDao(): ServiceDao
    abstract fun getBarberDao(): BarberDao

    abstract fun getAppointmentDao(): AppointmentDao
    abstract fun getAppointmentWithListOfServiceDao(): AppointmentWithListOfServiceDao

    abstract fun getAppointmentWithServiceCrossRefDao(): AppointmentWithServiceCrossRefDao

    companion object{
        private const val DB_NAME="SaloonApp"
        private var DATABASE_INSTANCE : AppDatabase?=null

        fun getAppDatabase(context: Context): AppDatabase?{
            if(DATABASE_INSTANCE ==null){
                DATABASE_INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }


            return DATABASE_INSTANCE
        }

    }
}