package app.pokedex.shared.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.serialization
import kotlinx.serialization.json.Json

internal expect fun createHttpClient(): HttpClient

internal fun createHttpClient(debuggable: Boolean): HttpClient = createHttpClient()
    .config {
        install(Resources)

        install(DefaultRequest) {
            url("https://pokeapi.co/api/v2/")
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            serialization(
                contentType = ContentType.Application.Json,
                format = Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
            )
        }

        if (debuggable) {
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.BODY
            }
        }
    }