package com.dlong.roomdemo.db.dao

import androidx.room.*
import com.dlong.roomdemo.db.bean.SongInfo

@Dao
interface SongDao {

    @Query("SELECT * FROM songinfo JOIN (SELECT * FROM groupwithsongrelation WHERE userId = :userId AND groupId = :groupId) groupwithsongrelation ON groupwithsongrelation.songId = SongInfo.songId WHERE SongInfo.userId=:userId")
    suspend fun susQueryGroupSong(userId: Long, groupId: Long): List<SongInfo>

    // 查询
    @Query("SELECT * FROM songinfo WHERE userId = :userId")
    suspend fun susQuery(userId: Long): List<SongInfo>

    // 插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(info: SongInfo): Long

    // 批量插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg info: SongInfo)

    // 修改
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(info: SongInfo)

    // 删除
    @Delete
    suspend fun delete(info: SongInfo)
}