package org.example.project.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.data.remote.response.CharacterResponse
import org.example.project.data.remote.response.CharactersWrapperResponse
import org.example.project.data.remote.response.EpisodesWrapperResponse
import org.example.project.data.remote.response.InfoResponse

class ApiService(
    private val client: HttpClient
) {

    suspend fun getSingleCharacter(id: String): CharacterResponse {
        return client.get("/api/character/$id").body()
    }

    suspend fun getAllCharacters(page: Int): CharactersWrapperResponse {
       return client.get("/api/character/") {
            parameter("page", page)
        }.body()
    }

    suspend fun getAllEpisodes(page: Int): EpisodesWrapperResponse {
        return client.get("/api/episode/") {
            parameter("page", page)
        }.body()
    }
}