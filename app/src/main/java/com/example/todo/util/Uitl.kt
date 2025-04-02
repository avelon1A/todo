package com.example.todo.util

import android.app.Activity
import android.provider.Settings
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlin.math.roundToInt

fun Activity.getSystemAnimationDuration(
    animDuration: Int = AnimationConstants.DefaultDurationMillis
): Int {
    return animDuration * getAnimationDurationScale().roundToInt()
}
@Composable
private fun getAnimationDurationScale(): Float {
    val activity = LocalContext.current as Activity
    return remember { activity.getAnimationDurationScale() }
}
private fun Activity.getAnimationDurationScale(): Float {
    return Settings.Global.getFloat(
        contentResolver,
        Settings.Global.ANIMATOR_DURATION_SCALE,
        1.0f
    )
}
fun Activity.changeSystemBarsColor(
    color: Color = Color.Transparent
) {
    if (window.statusBarColor != color.toArgb()) {
        window.statusBarColor = color.toArgb()
    }

    if (window.navigationBarColor != color.toArgb()) {
        window.navigationBarColor = color.toArgb()
    }
}
fun SystemUiController.changeSystemBarsColor(
    color: Color = Color.Transparent,
    darkIcons: Boolean = true
) {
    setSystemBarsColor(
        color = color,
        darkIcons = darkIcons
    )
}
@Composable
fun <T> systemTween(
    durationMillis: Int = AnimationConstants.DefaultDurationMillis,
    easing: Easing = FastOutSlowInEasing
): TweenSpec<T> = tween(
    durationMillis = durationMillis.times(getAnimationDurationScale()).toInt(),
    easing = easing
)