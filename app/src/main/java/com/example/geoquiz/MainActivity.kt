package com.example.geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.geoquiz.quiz.QuizViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                QuizScreen()
            }
        }
    }
}

@Composable
fun QuizScreen(vm: QuizViewModel = viewModel()) {
    val uiState by vm.uiState.collectAsState()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "GeoQuiz",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )

            Surface(
                tonalElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = vm.currentQuestionText(),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            if (!uiState.answered) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(onClick = { vm.answer(true) }) { Text("True") }
                    Button(onClick = { vm.answer(false) }) { Text("False") }
                }
            }

            val hideNext = uiState.answered && vm.isLastQuestion()
            if (!hideNext) {
                Button(
                    onClick = { vm.next() },
                    enabled = !vm.isLastQuestion()
                ) {
                    Text("Next")
                }
            }

            LinearProgressIndicator(
                progress = (uiState.currentIndex + 1f) / vm.totalQuestions,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "${uiState.currentIndex + 1} / ${vm.totalQuestions}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
