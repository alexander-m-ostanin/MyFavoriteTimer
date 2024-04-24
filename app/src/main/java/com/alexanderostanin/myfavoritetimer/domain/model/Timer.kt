package com.alexanderostanin.myfavoritetimer.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexanderostanin.myfavoritetimer.utils.Constants.TIMER_TABLE_NAME

@Entity(tableName = TIMER_TABLE_NAME)
data class Timer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "duration")
    val duration: Long = 0,

    //non-persistent attributes
//    var timeLeft: Long? = null,
//    val createdDate: Date? = null
)
