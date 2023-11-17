package com.zaidi.alarmDemo

import java.time.LocalDateTime

data class AlarmItem(
    val interval: LocalDateTime,
    val message: String
)
