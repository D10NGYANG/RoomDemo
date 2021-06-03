package com.dlong.roomdemo.app

import android.app.Application
import com.dlong.roomdemo.db.AppDB

class MyApp: Application() {

    companion object{
        private lateinit var mInstance: MyApp
        /** 获取实例 */
        fun instance() : MyApp = mInstance
    }

    /** 数据库 */
    lateinit var db: AppDB

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        // 初始化数据
        db = AppDB.getDatabase(this)
    }
}