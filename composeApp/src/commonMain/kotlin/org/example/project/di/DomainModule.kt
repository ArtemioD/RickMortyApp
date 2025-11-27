package org.example.project.di

import org.example.project.domain.usecase.GetRandomCharacter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {

    factoryOf(::GetRandomCharacter)
}