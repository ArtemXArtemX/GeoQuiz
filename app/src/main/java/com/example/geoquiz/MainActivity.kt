package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.geoquiz.quiz.QuizViewModel
import androidx.compose.runtime.getValue
import androidx.compose.material3.LinearProgressIndicator
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { QuizScreen() } }
    }
}


@Composable
fun QuizScreen(vm: QuizViewModel = viewModel()) {
    val uiState by vm.uiState.collectAsState()


    Scaffold { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("GeoQuiz", style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)


            Surface(tonalElevation = 2.dp, modifier = Modifier.fillMaxWidth()) {
                Text(vm.currentQuestionText(), modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
            }


            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { vm.answer(true) }) { Text("True") }
                Button(onClick = { vm.answer(false) }) { Text("False") }
            }


            Button(onClick = { vm.next() }) { Text("Next") }


            LinearProgressIndicator(progress = (uiState.currentIndex + 1f) / vm.totalQuestions, modifier = Modifier.fillMaxWidth())
            Text("${uiState.currentIndex + 1} / ${vm.totalQuestions}")
        }
    }
}