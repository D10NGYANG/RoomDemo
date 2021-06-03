package com.dlong.roomdemo.db.dao

import androidx.room.*
import com.dlong.roomdemo.db.relation.GroupWithSongRelation

@Dao
interface GroupWithSongDao {

    // 插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(info: GroupWithSongRelation): Long

    // 修改
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(info: GroupWithSongRelation)

    // 删除
    @Delete
    suspend fun delete(info: GroupWithSongRelation)
}