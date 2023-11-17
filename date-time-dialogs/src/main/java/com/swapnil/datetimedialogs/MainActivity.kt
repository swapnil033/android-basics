package com.swapnil.datetimedialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.swapnil.datetimedialogs.ui.theme.AndroidPracticeTheme
import java.util.Calendar
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    TimeBox()
                }
            }
        }
    }
}

@Composable
fun DateBox(
    date: Date = Date()
) {
    val cal = Calendar.getInstance()
    cal.time = date

    val selectedYear = cal[Calendar.YEAR]
    val selectedMonth = cal[Calendar.MONTH]
    val selectedDay = cal[Calendar.DAY_OF_MONTH]

    val context = LocalContext.current

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            Toast.makeText(context, "$dayOfMonth/${month + 1}/$year, day, month, year", Toast.LENGTH_SHORT).show()
        }, selectedYear, selectedMonth, selectedDay
    )

    datePickerDialog.show()

}

@Composable
fun TimeBox(
    date: Date = Date()
) {

    val cal = Calendar.getInstance()
    cal.time = date

    val selectedHourOfDay = cal[Calendar.HOUR_OF_DAY]
    val selectedMinute = cal[Calendar.MINUTE]

    val context = LocalContext.current

    val timePickerDialog = TimePickerDialog(
        context,
        { _: TimePicker, hourOfDay: Int, minute: Int ->


            Toast.makeText(context, "$hourOfDay/$minute, hour, minute", Toast.LENGTH_SHORT).show()
        }, selectedHourOfDay, selectedMinute, false
    )

    timePickerDialog.show()

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidPracticeTheme {
        Greeting("Android")
    }
}