package app.pokedex.shared.util

import kotlin.contracts.InvocationKind.AT_MOST_ONCE
import kotlin.contracts.contract

/**
 * Returns `true` if this instance represents a successful outcome. In this case [isFailure] returns `false`.
 */
fun <R, C> Either<R, C>.isSuccess(): Boolean {
    contract {
        returns(true) implies (this@isSuccess is Either.Success)
        returns(false) implies (this@isSuccess is Either.Failure)
    }
    return this is Either.Success
}

/**
 * Returns `true` if this instance represents a failed outcome. In this case [isSuccess] returns `false`.
 */
fun <R, C> Either<R, C>.isFailure(): Boolean {
    contract {
        returns(true) implies (this@isFailure is Either.Failure)
        returns(false) implies (this@isFailure is Either.Success)
    }
    return this is Either.Failure
}

/**
 * Returns the result of [onSuccess] for the encapsulated result if this instance represents [success][Either.isSuccess]
 * or the result of [onFailure] function for the encapsulated result if it is [failure][Either.isFailure].
 */
inline fun <R, C, T> Either<R, C>.fold(
    onSuccess: (result: R) -> T,
    onFailure: (cause: C) -> T,
): T {
    contract {
        callsInPlace(onSuccess, AT_MOST_ONCE)
        callsInPlace(onFailure, AT_MOST_ONCE)
    }
    return when (this) {
        is Either.Success -> onSuccess(result)
        is Either.Failure -> onFailure(cause)
    }
}

/**
 * The given function is applied if this is a [Success][Either.Success].
 */
inline fun <R, C, T> Either<R, C>.map(transform: (result: R) -> T): Either<T, C> {
    contract { callsInPlace(transform, AT_MOST_ONCE) }
    return when (this) {
        is Either.Success -> Either.Success(transform(result))
        is Either.Failure -> this
    }
}

/**
 * The given function is applied if this is a [Failure][Either.Failure].
 */
inline fun <R, C, T> Either<R, C>.mapFailure(transform: (cause: C) -> T): Either<R, T> {
    contract { callsInPlace(transform, AT_MOST_ONCE) }
    return when (this) {
        is Either.Success -> this
        is Either.Failure -> Either.Failure(transform(cause))
    }
}

/**
 * Transforms the result from the original [Either] by applying transform if it's [success][Either.Success],
 * and returns another [Either].
 */
inline fun <R, C, T> Either<R, C>.flatMap(transform: (result: R) -> Either<T, C>): Either<T, C> {
    contract { callsInPlace(transform, AT_MOST_ONCE) }
    return when (this) {
        is Either.Success -> transform(this.result)
        is Either.Failure -> this
    }
}
