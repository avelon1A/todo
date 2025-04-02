package com.example.todo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.todo.ui.AppNavHost
import com.example.todo.ui.theme.TodoTheme
import com.example.todo.util.changeSystemBarsColor
import com.example.todo.util.getSystemAnimationDuration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setupSystemSplashScreen()

        super.onCreate(savedInstanceState)
        actionBar?.hide()
        enableEdgeToEdge()
        setContent {
            TodoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   AppNavHost(
                       modifier = Modifier.padding(innerPadding)
                   )
                }
            }
        }
    }
    private fun setupSystemSplashScreen() {
        installSplashScreen().setOnExitAnimationListener { splashScreenView ->
            val fadeAnim = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.ALPHA,
                1f,
                0f
            )

            with(fadeAnim) {
                interpolator = LinearInterpolator()
                duration = getSystemAnimationDuration().toLong()
                doOnStart { changeSystemBarsColor() }
                doOnEnd { splashScreenView.remove() }
                start()
            }
        }
    }
}


