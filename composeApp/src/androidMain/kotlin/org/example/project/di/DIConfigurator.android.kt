package org.example.project.di

import org.example.project.data.database.RickMortyDatabase
import org.example.project.data.database.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single<RickMortyDatabase> { getDatabase(get()) }
    }
}