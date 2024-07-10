package app.pokedex.shared.domain.error

/**
 * Represents different types of errors that can occur when fetching Pokémon information.
 *
 * - [ServiceUnavailable] indicates that the our network is down
 * - [ClientError] indicates that the we are screwed somewhere xD
 * - [ServerError] indicates that the they are screwed somewhere :D
 * - [UnknownError] I don't know what to say about this error
 */
enum class GetPokemonInfoError {
    ServiceUnavailable,
    ClientError,
    ServerError,
    UnknownError,
}