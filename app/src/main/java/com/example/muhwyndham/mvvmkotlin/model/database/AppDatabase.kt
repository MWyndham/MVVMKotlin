package com.example.muhwyndham.mvvmkotlin.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.muhwyndham.mvvmkotlin.model.Post
import com.example.muhwyndham.mvvmkotlin.model.PostDao

@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun postDao(): PostDao
}