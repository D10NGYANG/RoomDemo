package com.dlong.roomdemo.db.bean

import androidx.room.Entity
import java.io.Serializable

/**
 * ζ­ε
 * @Author: D10NG
 * @Time: 2021/6/3 3:32 δΈε
 */
@Entity(
    primaryKeys = ["groupId", "userId"]
)
data class GroupInfo(
    var groupId: Long = 0,

    var userId: Long = 0,

    var name: String = "",
): Serializable
