package app.pokedex.shared.domain.error

/**
 * Represents different types of exceptions that can occur when fetching Pokémon information.
 *
 * - [NoInternetException] indicates that the our network is down
 * - [ClientSideException] indicates that the we are screwed somewhere xD
 * - [ServerSideException] indicates that the they are screwed somewhere :D
 * - [UnknownException] I don't know what to say about this error :P
 */
sealed class PokedexException @JvmOverloads constructor(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Throwable() {
    class NoInternetException : PokedexException(message = "No internet connection")
    class ClientSideException : PokedexException(message = "Client side error")
    class ServerSideException : PokedexException(message = "Server side error")
    class UnknownException(override val cause: Throwable) : PokedexException()
}