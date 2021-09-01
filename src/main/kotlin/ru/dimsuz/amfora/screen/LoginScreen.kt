package ru.dimsuz.amfora.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dimsuz.amfora.Screen

class LoginScreen : Screen {

  @Composable
  override fun Content() {
    var state by remember { mutableStateOf(State()) }

    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      TextField(
        label = { Text("Login") },
        value = state.login,
        onValueChange = { state = state.copy(login = it) }
      )
      Spacer(modifier = Modifier.height(16.dp))
      TextField(
        label = { Text("Password") },
        value = state.password,
        onValueChange = { state = state.copy(password = it) }
      )
      Spacer(modifier = Modifier.height(16.dp))
      Button(onClick = {}) {
        Text(text = "Login")
      }
    }
  }

  @Immutable
  private data class State(
    val login: TextFieldValue = TextFieldValue(),
    val password: TextFieldValue = TextFieldValue(),
  )
}
