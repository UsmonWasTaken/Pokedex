package app.pokedex.shared.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java

internal actual fun createHttpClient(): HttpClient {
    return HttpClient(Java)
}