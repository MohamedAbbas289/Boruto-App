package com.example.borutoapp.di

import android.content.Context
import com.example.borutoapp.data.manager.LocalManagerImpl
import com.example.borutoapp.data.repository.Repository
import com.example.borutoapp.domain.manager.LocalManager
import com.example.borutoapp.domain.use_cases.UseCases
import com.example.borutoapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.borutoapp.domain.use_cases.read_onBoarding.ReadOnBoardingUseCase
import com.example.borutoapp.domain.use_cases.save_onBoarding.SaveOnBoardingUseCase
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

    @Provides
    @Singleton
    fun provideUseCases(repo: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repo),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repo),
            getAllHeroesUseCase = GetAllHeroesUseCase(repo)
        )
    }
}