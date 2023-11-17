package com.zaidi.alarmDemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zaidi.alarmDemo.ui.theme.IntentPracticeAppTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scheduler = AndroidAlarmScheduler(this)
        var alarmItem: AlarmItem? = null

        setContent {
            IntentPracticeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    var secondsText by remember {
                        mutableStateOf("")
                    }
                    var messageText by remember {
                        mutableStateOf("")
                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        TextField(
                            value = secondsText,
                            onValueChange = { secondsText = it },
                            label = { Text(text = "Interval (in seconds)") }
                        )

                        TextField(
                            value = messageText,
                            onValueChange = { messageText = it },
                            label = { Text(text = "message") }
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(onClick = {

                                alarmItem = AlarmItem(
                                    interval = LocalDateTime.now().plusSeconds(secondsText.toLong()),
                                    message = messageText
                                )
                                alarmItem?.let( scheduler::schedule)
                                secondsText = ""
                                messageText = ""
                                alarmItem = null

                            }) {
                                Text(text = "Schedule")
                            }
                            Button(onClick = {
                                alarmItem?.let(scheduler::cancel)
                            }) {
                                Text(text = "Cancel")
                            }
                        }

                    }

                }
            }
        }
    }
}