package com.dlong.roomdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dlong.roomdemo.db.bean.GroupInfo
import com.dlong.roomdemo.db.bean.SongInfo
import com.dlong.roomdemo.db.bean.UserInfo
import com.dlong.roomdemo.db.dao.GroupDao
import com.dlong.roomdemo.db.dao.GroupWithSongDao
import com.dlong.roomdemo.db.dao.SongDao
import com.dlong.roomdemo.db.dao.UserDao
import com.dlong.roomdemo.db.relation.GroupWithSongRelation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 数据库
 * @Author: D10NG
 * @Time: 2021/6/3 3:37 下午
 */
@Database(
    entities = [
        UserInfo::class,
        GroupInfo::class,
        SongInfo::class,
        GroupWithSongRelation::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDB : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getGroupDao(): GroupDao

    abstract fun getSongDao(): SongDao

    abstract fun getGroupWithSongDao(): GroupWithSongDao

    companion object{
        @Volatile
        private var INSTANCE: AppDB? = null

        @JvmStatic
        fun getDatabase(context: Context): AppDB {
            val temp = INSTANCE
            if (null != temp) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "app.db"
                )
                    .addCallback(object : RoomDatabase.Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            GlobalScope.launch {
                                initInsert(INSTANCE!!)
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        /**
         * 初始化加载数据
         * @param db AppDB
         */
        private suspend fun initInsert(db: AppDB) {
            for (i in 0 .. 1) {
                db.getUserDao().insert(UserInfo(i.toLong(), "用户$i"))
                for (j in 0 .. 2) {
                    db.getGroupDao().insert(GroupInfo(j.toLong(), i.toLong(), "歌单$j"))
                }
                for (j in 0 .. 3) {
                    db.getSongDao().insert(SongInfo(j.toLong(), i.toLong(), "歌曲$j"))
                }
            }

            // 用户0，歌单0，有歌曲0
            db.getGroupWithSongDao().insert(GroupWithSongRelation(0, 0, 0))
            // 用户0，歌单1，有歌曲0,1
            db.getGroupWithSongDao().insert(GroupWithSongRelation(0, 1, 0))
            db.getGroupWithSongDao().insert(GroupWithSongRelation(0, 1, 1))
            // 用户0，歌单2，有歌曲0,1,2
            db.getGroupWithSongDao().insert(GroupWithSongRelation(0, 2, 0))
            db.getGroupWithSongDao().insert(GroupWithSongRelation(0, 2, 1))
            db.getGroupWithSongDao().insert(GroupWithSongRelation(0, 2, 2))

            // 用户1，歌单0，有歌曲1
            db.getGroupWithSongDao().insert(GroupWithSongRelation(1, 0, 1))
            // 用户1，歌单1，有歌曲1,2
            db.getGroupWithSongDao().insert(GroupWithSongRelation(1, 1, 1))
            db.getGroupWithSongDao().insert(GroupWithSongRelation(1, 1, 2))
            // 用户1，歌单2，有歌曲1,2,3
            db.getGroupWithSongDao().insert(GroupWithSongRelation(1, 2, 1))
            db.getGroupWithSongDao().insert(GroupWithSongRelation(1, 2, 2))
            db.getGroupWithSongDao().insert(GroupWithSongRelation(1, 2, 3))
        }
    }
}