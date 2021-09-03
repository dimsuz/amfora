package ru.dimsuz.amfora.screen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.toInput
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dimsuz.amfora.LoginMutation
import ru.dimsuz.amfora.Screen

class LoginScreen : Screen {
  @Composable
  override fun Content() {
    var state by remember { mutableStateOf(State()) }
    val scope = rememberCoroutineScope()

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
      Button(onClick = { scope.login(state.login.text, state.password.text) }) {
        Text(text = "Login")
      }
    }
  }

  @Immutable
  private data class State(
    val login: TextFieldValue = TextFieldValue(),
    val password: TextFieldValue = TextFieldValue(),
  )

  val apolloClient = ApolloClient.builder()
    .serverUrl("https://stoplight.io/graphql")
    .build()

  private fun CoroutineScope.login(email: String, password: String) {
    launch(Dispatchers.IO) {
      try {
        val result = apolloClient.mutate(LoginMutation(
          email.toInput(),
          password.toInput(),
          workspaceIntegrationId = 167699
        )).await()
        println("response: ${result.data}")
      } catch (e: ApolloException) {
        e.printStackTrace()
      }
    }
  }
}
