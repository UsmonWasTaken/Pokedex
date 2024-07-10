package app.pokedex.shared.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android

internal actual fun createHttpClient(): HttpClient {
    return HttpClient(Android)
}