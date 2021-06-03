package com.dlong.roomdemo.db.dao

import androidx.room.*
import com.dlong.roomdemo.db.bean.UserInfo

@Dao
interface UserDao {

    // 查询
    @Query("SELECT * FROM userinfo")
    suspend fun susQuery(): List<UserInfo>

    // 插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(info: UserInfo): Long

    // 批量插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg info: UserInfo)

    // 修改
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(info: UserInfo)

    // 删除
    @Delete
    suspend fun delete(info: UserInfo)
}