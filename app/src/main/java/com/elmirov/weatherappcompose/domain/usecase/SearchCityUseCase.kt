package com.elmirov.weatherappcompose.domain.usecase

import com.elmirov.weatherappcompose.domain.entity.City
import com.elmirov.weatherappcompose.domain.repository.SearchRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(
    private val repository: SearchRepository,
) {

    suspend operator fun invoke(query: String): List<City> = repository.search(query)
}