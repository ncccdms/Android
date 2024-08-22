package com.ncccdms.todolistbagian3.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ncccdms.todolistbagian3.data.AuthRepositoryImpl
import com.ncccdms.todolistbagian3.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthRepository = AuthRepositoryImpl(
        auth = auth,
        firestore = firestore
    )
}

