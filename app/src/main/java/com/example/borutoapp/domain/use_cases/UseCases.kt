package com.example.borutoapp.domain.use_cases

import com.example.borutoapp.domain.use_cases.read_onBoarding.ReadOnBoardingUseCase
import com.example.borutoapp.domain.use_cases.save_onBoarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase
)