package ru.dimsuz.amfora

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.flow.flowOf
import ru.dimsuz.amfora.screen.LoginScreen

interface Screen {
  @Composable
  fun Content()
}

object EmptyScreen : Screen {
  @Composable
  override fun Content() {
  }
}

@ExperimentalComposeUiApi
fun main() = application {
  Window(
    onCloseRequest = { exitApplication() },
    onKeyEvent = { if (it.isCtrlPressed && it.key == Key.Q) { exitApplication(); true } else false },
    title = "Amfora",
  ) {
    val screens = flowOf<Screen>(LoginScreen())

    MaterialTheme {
      screens.collectAsState(initial = EmptyScreen)
        .value
        .Content()
    }
  }
}
