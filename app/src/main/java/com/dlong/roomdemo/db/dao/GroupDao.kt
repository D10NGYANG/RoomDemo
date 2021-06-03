package com.dlong.roomdemo.db.dao

import androidx.room.*
import com.dlong.roomdemo.db.bean.GroupInfo
import com.dlong.roomdemo.db.relation.GroupWithSongData

@Dao
interface GroupDao {

    // 查询歌单带歌曲信息
    @Transaction
    @Query("SELECT * FROM groupinfo WHERE userId = (:userId) AND groupId = (:groupId)")
    suspend fun susQueryGroup(userId: Long, groupId: Long): GroupWithSongData?

    // 查询
    @Query("SELECT * FROM groupinfo WHERE userId = :userId")
    suspend fun susQuery(userId: Long): List<GroupInfo>

    // 插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(info: GroupInfo): Long

    // 批量插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg info: GroupInfo)

    // 修改
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(info: GroupInfo)

    // 删除
    @Delete
    suspend fun delete(info: GroupInfo)
}