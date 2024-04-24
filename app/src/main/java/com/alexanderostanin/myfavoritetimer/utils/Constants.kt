package com.alexanderostanin.myfavoritetimer.utils

object Constants {

    //Database
    const val TIMER_DATABASE_NAME = "timer_db"
    const val TIMER_TABLE_NAME = "timer_table"

    //==Service==
    const val SERVICE_BROADCAST_INTENT = "com.alexanderostanin.myfavoritetimer.service.broadcast"

    const val SERVICE_ACTION_INIT = "service_action_on_init"
    const val SERVICE_ACTION_START = "service_action_on_start"
    const val SERVICE_ACTION_PAUSE = "service_action_on_pause"
    const val SERVICE_ACTION_RESET = "service_action_on_reset"
    const val SERVICE_ACTION_STOP = "service_action_on_stop"

    const val SERVICE_TIME_INITIAL = "service_initial_time"
    const val SERVICE_TIME_LEFT = "service_left_long_extra"
    const val SERVICE_TIME_DURATION_TEXT = "service_duration_text"
    const val SERVICE_TIME_PROGRESS = "service_progress"

    const val SERVICE_TIMER_STATE = "service_timer_state"

    //==Notification==
    const val NOTIFICATION_CHANNEL_NAME = "timer_notification_channel"

    //==EditTimer==
    const val ICON_BUTTON_ALPHA_INITIAL = 0.5f
    const val CONTAINER_BACKGROUND_ALPHA_INITIAL = 0.6f

    const val INCREASE_ACTION_TYPE = "increase"
    const val DECREASE_ACTION_TYPE = "decrease"

    const val HOURS_TYPE = "hours"
    const val MINUTES_TYPE = "minutes"
    const val SECONDS_TYPE = "seconds"

    const val HOURS_INTERVAL = 1
    const val MINUTES_INTERVAL = 1
    const val SECONDS_INTERVAL = 5

}