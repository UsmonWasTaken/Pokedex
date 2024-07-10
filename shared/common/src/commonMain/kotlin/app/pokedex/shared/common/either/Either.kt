package app.pokedex.shared.common.either

import kotlin.contracts.InvocationKind
import kotlin.contracts.InvocationKind.AT_MOST_ONCE
import kotlin.contracts.contract
import kotlin.coroutines.cancellation.CancellationException

/**
 * The [Either] is an interface that represents a value
 * that can be in one of two states: either a success or a failure.
 *
 * The Either class has two subclasses: [Success][Either.Success] and [Failure][Either.Failure].
 *
 * @see Either.Success
 * @see Either.Failure
 */
sealed interface Either<out R, out C> {

    /**
     * The [Either.Success] is a inline value class that implements the [Either] interface and represents a success.
     * It contains a [result] of the type specified by the [R] parameter.
     */
    @JvmInline
    value class Success<R>(val result: R) : Either<R, Nothing>

    /**
     * The [Either.Failure] is a inline value class that implements the [Either] interface and represents a failure.
     * It contains a [cause] of the type specified by the [C] parameter.
     */
    @JvmInline
    value class Failure<C>(val cause: C) : Either<Nothing, C>
}

/**
 * This function used to wrap objects with [Either] class.
 *
 * @return [Either.Success] result as [Either]
 */
fun <R> R.asSuccess(): Either<R, Nothing> = Either.Success(this)

/**
 * This function used to wrap objects with [Either] class.
 *
 * @return [Either.Failure] result as [Either]
 */
fun <C> C.asFailure(): Either<Nothing, C> = Either.Failure(this)

/**
 * Executes the specified function [block] and returns its encapsulated result as a `Success` value if the invocation is successful.
 * If an exception is thrown during the execution of [block], it catches the [Throwable] exception and encapsulates it as a `Failure` value.
 *
 * @param block The function to execute.
 * @return An `Either` value containing either the successful result or the caught exception.
 *
 * ### Notes:
 * - This function ensures that the [block] is only executed once, even if it throws an exception.
 * - Cancellation exceptions are not caught and are rethrown to the caller.
 */
inline fun <R> catch(block: () -> R): Either<R, Throwable> = try {
    Either.Success(result = block())
} catch (cancellation: CancellationException) {
    throw cancellation
} catch (throwable: Throwable) {
    Either.Failure(cause = throwable)
}

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
 * Returns the result of [onSuccess] for the encapsulated result if this instance represents a [success][Either.isSuccess]
 * or the result of [onFailure] function for the encapsulated result if it is a [failure][Either.isFailure].
 *
 * @param onSuccess a function that takes the encapsulated result and returns a value of type `T`
 * @param onFailure a function that takes the encapsulated cause and returns a value of type `T`
 *
 * @return the result of [onSuccess] or [onFailure] function
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
 * Applies the given [transform] function to the success value of this [Either] if it is a [Success][Either.Success].
 *
 * If this is a [Failure][Either.Failure], it is returned unchanged.
 *
 * @param transform The function to apply to the success value.
 * @return A new [Either] with the transformed success value or the original [failure][Either.Failure].
 */
inline fun <R, C, T> Either<R, C>.map(transform: (result: R) -> T): Either<T, C> {
    contract { callsInPlace(transform, AT_MOST_ONCE) }
    return when (this) {
        is Either.Success -> Either.Success(transform(result))
        is Either.Failure -> this
    }
}

/**
 * Returns a new [Either] instance with the cause of the [Failure][Either.Failure] transformed by the given [transform] function.
 *
 * If this is a [Success][Either.Success], it is returned unchanged.
 *
 * @param transform The function to transform the cause of the [Failure][Either.Failure].
 *
 * @return A new [Either] instance with the transformed cause.
 */
inline fun <R, C, T> Either<R, C>.mapFailure(transform: (cause: C) -> T): Either<R, T> {
    contract { callsInPlace(transform, InvocationKind.AT_MOST_ONCE) }
    return when (this) {
        is Either.Success -> this
        is Either.Failure -> Either.Failure(transform(cause))
    }
}

/**
 * Transforms the result from the original [Either] by applying [transform] if it's [success][Either.Success],
 * and returns another [Either].
 *
 * If the original [Either] is a [failure][Either.Failure], it is returned as is.
 * This function is similar to `map()` but returns an [Either] instead of a value.
 *
 * @param transform The function to apply to the result if it's a success.
 *
 * @return A new [Either] with the transformed result or the original failure.
 */
inline fun <R, C, T> Either<R, C>.flatMap(transform: (result: R) -> Either<T, C>): Either<T, C> {
    contract { callsInPlace(transform, AT_MOST_ONCE) }
    return when (this) {
        is Either.Success -> transform(result)
        is Either.Failure -> this
    }
}

/**
 * Transforms the result from the original [Either] by applying [transform] if it's [Either.Failure],
 * and returns another [Either].
 *
 * If the original [Either] is [Either.Success], it is returned as-is.
 * If the original [Either] is [Either.Failure], the [transform] function is applied to the cause of the failure,
 * and the result of that transformation is returned as a new [Either].
 *
 * @param transform A function that transforms the cause of the failure into a new [Either].
 *
 * @return A new [Either] with the transformed result.
 */
inline fun <R, C, T> Either<R, C>.flatMapFailure(transform: (result: C) -> Either<R, T>): Either<R, T> {
    contract { callsInPlace(transform, AT_MOST_ONCE) }
    return when (this) {
        is Either.Success -> this
        is Either.Failure -> transform(cause)
    }
}
