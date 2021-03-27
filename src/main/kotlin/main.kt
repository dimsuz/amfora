import androidx.compose.desktop.AppManager
import androidx.compose.desktop.Window
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.window.KeyStroke
import androidx.compose.ui.window.Menu
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.MenuItem
import kotlinx.coroutines.flow.flowOf

interface Screen {
  @Composable
  fun Content()
}

class LogScreen(val dependency: Unit) : Screen {
  @Composable
  override fun Content() {
    Button(onClick = {}) {
      Text("Hello, World")
    }
  }
}

object EmptyScreen : Screen {
  @Composable
  override fun Content() {
  }
}

fun main() = Window(
  title = "Amfora",
  menuBar = buildMenuBar(),
) {

  val screens = flowOf<Screen>(LogScreen(Unit))

  MaterialTheme {
    screens.collectAsState(initial = EmptyScreen)
      .value
      .Content()
  }
}

private fun buildMenuBar(): MenuBar {
  return MenuBar(
    Menu(
      "File",
      MenuItem("Settings", onClick = { }, shortcut = KeyStroke(Key.S)),
      MenuItem("Quit", onClick = { AppManager.exit() }, shortcut = KeyStroke(Key.Q)),
    ),
  )
}
