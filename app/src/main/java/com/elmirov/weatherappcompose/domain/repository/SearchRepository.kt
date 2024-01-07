package com.elmirov.weatherappcompose.domain.repository

import com.elmirov.weatherappcompose.domain.entity.City

interface SearchRepository {

    suspend fun search(query: String): List<City>
}