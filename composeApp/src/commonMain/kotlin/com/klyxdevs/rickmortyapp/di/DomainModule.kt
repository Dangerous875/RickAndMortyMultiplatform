package com.klyxdevs.rickmortyapp.di

import com.klyxdevs.rickmortyapp.domain.GetAllCharactersUseCase
import com.klyxdevs.rickmortyapp.domain.GetRandomCharacterUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetRandomCharacterUseCase)
    factoryOf(::GetAllCharactersUseCase)
}