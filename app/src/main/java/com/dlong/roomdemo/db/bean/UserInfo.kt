package com.dlong.roomdemo.db.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * 用户信息
 * @Author: D10NG
 * @Time: 2021/6/3 3:28 下午
 */
@Entity
data class UserInfo(
    @PrimaryKey
    var userId: Long = 0,

    var name: String = ""
): Serializable
