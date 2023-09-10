package com.example.shaloonapp.di

import com.example.shaloonapp.model.repository.IRepository
import com.example.shaloonapp.model.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository : Repository): IRepository

}
