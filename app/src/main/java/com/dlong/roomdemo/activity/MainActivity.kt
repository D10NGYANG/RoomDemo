package com.dlong.roomdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.dlong.roomdemo.R
import com.dlong.roomdemo.app.MyApp
import com.dlong.roomdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        GlobalScope.launch {
            MyApp.instance().db.getUserDao().susQuery()
        }

        // relation 查询法
        binding.button.setOnClickListener {
            val dao = MyApp.instance().db.getGroupDao()
            GlobalScope.launch {
                val data = dao.susQueryGroup(0, 1)
                val builder = StringBuilder("relation 查询法\n")
                if (data == null) {
                    builder.append("查询为空\n")
                } else {
                    builder.append("歌单=${data.group}\n")
                    for (item in data.songs) {
                        builder.append("歌曲=$item\n")
                    }
                }
                withContext(Dispatchers.Main) {
                    binding.resultText = builder.toString()
                }
            }
        }

        // join 查询法
        binding.button2.setOnClickListener {
            val dao = MyApp.instance().db.getSongDao()
            GlobalScope.launch {
                val data = dao.susQueryGroupSong(0, 1)
                val builder = StringBuilder("join 查询法\n")
                for (item in data) {
                    builder.append("歌曲=$item\n")
                }
                withContext(Dispatchers.Main) {
                    binding.resultText = builder.toString()
                }
            }
        }
    }
}