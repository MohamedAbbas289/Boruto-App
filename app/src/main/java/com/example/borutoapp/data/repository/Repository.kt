package com.example.borutoapp.data.repository

import com.example.borutoapp.domain.manager.LocalManager
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class Repository @Inject constructor(
    private val localManager: LocalManager
) {
    suspend fun saveOnBoardingState(completed: Boolean) {
        localManager.saveOnBoardingState(completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return localManager.readOnBoardingState()
    }
}