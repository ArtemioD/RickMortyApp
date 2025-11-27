package org.example.project.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.data.RepositoryImpl
import org.example.project.data.remote.ApiService
import org.example.project.data.remote.paging.CharactersPagingSource
import org.example.project.data.remote.paging.EpisodesPagingSource
import org.example.project.domain.Repository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "rickandmortyapi.com"
                    //parameters.append("key", "")
                }
            }
        }
    }
    //factory { ApiService(get()) } // forma antigua
    factoryOf(::ApiService) // forma nueva
    factory<Repository> {
        RepositoryImpl(
            get(),
            get(),
            get(),
            get()
        )
    } // para las interfaces usar la forma vieja !!
    //factoryOf(::RepositoryImpl) bind Repository::class // o esta !!
    factoryOf(::CharactersPagingSource)
    factoryOf(::EpisodesPagingSource)

}