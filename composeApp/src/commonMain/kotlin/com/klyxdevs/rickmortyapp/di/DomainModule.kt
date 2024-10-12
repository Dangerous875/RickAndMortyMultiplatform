package com.klyxdevs.rickmortyapp.di

import com.klyxdevs.rickmortyapp.domain.GetRandomCharacter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetRandomCharacter)
}