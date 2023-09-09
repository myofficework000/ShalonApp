package com.example.shaloonapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shaloonapp.model.dao.ServiceDao
import com.example.shaloonapp.model.dao.UserDao
import com.example.shaloonapp.model.dto.Service
import com.example.shaloonapp.model.dto.User

@Database(entities = [User::class, Service::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getServiceDao(): ServiceDao

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