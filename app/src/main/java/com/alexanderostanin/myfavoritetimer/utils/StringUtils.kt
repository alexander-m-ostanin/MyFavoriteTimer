package com.alexanderostanin.myfavoritetimer.utils

import java.util.concurrent.TimeUnit

fun longToTimeString(milliseconds: Long): String{
    val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
    val min = TimeUnit.MILLISECONDS.toMinutes(milliseconds) % 60
    val sec = TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60
    return String.format("%02d:%02d:%02d", hours, min, sec)
}

fun longToHoursInt(milliseconds: Long) =
    TimeUnit.MILLISECONDS.toHours(milliseconds).toInt()

fun longToMinutesInt(milliseconds: Long) =
    (TimeUnit.MILLISECONDS.toMinutes(milliseconds) % 60).toInt()

fun longToSecondsInt(milliseconds: Long) =
    (TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60).toInt()

fun timeStringToLong(hours: Int, minutes: Int, seconds: Int): Long{
    return TimeUnit.HOURS.toMillis(hours.toLong()) +
            TimeUnit.MINUTES.toMillis(minutes.toLong()) +
            TimeUnit.SECONDS.toMillis(seconds.toLong())
}