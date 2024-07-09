package app.pokedex.shared.util

import java.io.Serializable
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
sealed interface Either<out R, out C> : Serializable {

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
 * Calls the specified function [block] and returns its encapsulated result if invocation was successful,
 * catching any [Throwable] exception that was thrown from the [block] function execution and encapsulating it as a failure.
 */
inline fun <R> catch(block: () -> R): Either<R, Throwable> = try {
    Either.Success(result = block())
} catch (cancellation: CancellationException) {
    throw cancellation
} catch (throwable: Throwable) {
    Either.Failure(cause = throwable)
}
