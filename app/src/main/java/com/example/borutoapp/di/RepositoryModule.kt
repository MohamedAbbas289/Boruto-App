package com.example.borutoapp.di

import android.content.Context
import com.example.borutoapp.data.manager.LocalManagerImpl
import com.example.borutoapp.domain.manager.LocalManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): LocalManager {
        return LocalManagerImpl(context)
    }

//    @Provides
//    @Singleton
//    // one function that passes the repo to all use cases we have
//    fun provideUseCases(repo: Repository): UseCases{
//        return UseCases(
//            saveOnBoardingUseCase = SaveOnBoardingUseCase(repo),
//            readOnBoardingUseCase = ReadOnBoardingUseCase(repo)
//        )
//    }
}