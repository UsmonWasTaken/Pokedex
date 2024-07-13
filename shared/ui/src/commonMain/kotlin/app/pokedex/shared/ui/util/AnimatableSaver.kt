package app.pokedex.shared.ui.util

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope

object AnimatableSaver : Saver<Animatable<Float, AnimationVector1D>, Float> {
    override fun restore(value: Float): Animatable<Float, AnimationVector1D> = Animatable(value)
    override fun SaverScope.save(value: Animatable<Float, AnimationVector1D>): Float = value.value
}