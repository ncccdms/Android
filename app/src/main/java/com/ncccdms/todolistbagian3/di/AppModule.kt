package com.ncccdms.todolistbagian3.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ncccdms.todolistbagian3.data.AuthRepositoryImpl
import com.ncccdms.todolistbagian3.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {
    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(
        auth = Firebase.auth
    )
}