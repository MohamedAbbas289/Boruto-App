package com.example.borutoapp.data.repository

import com.example.borutoapp.domain.manager.LocalManager
import com.example.borutoapp.domain.repository.RemoteDataSource
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localManager: LocalManager
) {
    fun getAllHeroes() = remoteDataSource.getAllHeroes()

    suspend fun saveOnBoardingState(completed: Boolean) {
        localManager.saveOnBoardingState(completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return localManager.readOnBoardingState()
    }
}