package com.example.borutoapp.domain.use_cases.get_all_heroes

import com.example.borutoapp.data.repository.Repository

class GetAllHeroesUseCase(private val repository: Repository) {
    operator fun invoke() = repository.getAllHeroes()
}