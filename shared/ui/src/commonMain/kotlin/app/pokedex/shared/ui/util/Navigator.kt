package app.pokedex.shared.ui.util

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.stack.Stack
import cafe.adriel.voyager.navigator.Navigator

/**
 * Pushes a [Screen] onto the navigation stack if it is not already the topmost screen.
 *
 * This function helps prevent duplicate screens from being pushed onto the stack,
 * which can lead to unexpected navigation behavior.
 *
 * @param screen The [Screen] to push onto the stack.
 */
infix fun Navigator.safePush(screen: Screen) {
    for (item in items) if (item.key == screen.key) return
    push(screen)
}

/**
 * Safely pops the current screen from the stack if it exists.
 *
 * Iterates through the items in the stack and checks if the key of the current screen matches any item's key.
 * If a match is found, it calls the `pop()` function to remove the screen from the stack and returns `true`.
 * Otherwise, it returns `false` indicating that the screen was not found in the stack.
 *
 * @return `true` if the screen was successfully popped, `false` otherwise.
 */
context(Screen)
fun Stack<Screen>.safePop(): Boolean {
    for (item in items) if (item.key == key) return pop()
    return false
}
