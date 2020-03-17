package com.mpcreative.demo.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class  Task_entity : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "task")
    var task: String = ""

    @ColumnInfo(name = "desc")
    var desc: String = ""

    @ColumnInfo(name = "finish_by")
     var finishBy: String? = null

    @ColumnInfo(name = "finished")
     var finished = false
}