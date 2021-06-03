package com.dlong.roomdemo.db.relation

import androidx.room.*
import com.dlong.roomdemo.db.bean.GroupInfo
import com.dlong.roomdemo.db.bean.SongInfo
import java.io.Serializable

/**
 * 歌单与歌的关系
 * @Author: D10NG
 * @Time: 2021/6/3 3:44 下午
 */
@Entity(
    primaryKeys = ["userId", "groupId", "songId"],
    foreignKeys = [ForeignKey(entity = GroupInfo::class, parentColumns = ["groupId", "userId"], childColumns = ["groupId", "userId"], onDelete = ForeignKey.CASCADE)]
)
data class GroupWithSongRelation(
    var userId: Long = 0,
    var groupId: Long = 0,
    var songId: Long = 0
): Serializable

/**
 * 歌单与歌的查询结果
 * @Author: D10NG
 * @Time: 2021/6/3 3:49 下午
 */
data class GroupWithSongData(
    @Embedded
    val group: GroupInfo,

    @Relation(parentColumn = "groupId",
        entityColumn = "songId",
        associateBy = Junction(GroupWithSongRelation::class)
    )
    var songs: List<SongInfo>
): Serializable
