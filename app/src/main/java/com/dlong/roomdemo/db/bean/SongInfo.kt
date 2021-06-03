package com.dlong.roomdemo.db.bean

import androidx.room.Entity
import java.io.Serializable

/**
 * 歌曲
 * @Author: D10NG
 * @Time: 2021/6/3 3:35 下午
 */
@Entity(
    primaryKeys = ["songId", "userId"]
)
data class SongInfo(
    var songId: Long = 0,

    var userId: Long = 0,

    var name: String = ""
): Serializable
