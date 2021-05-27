package com.example.kotlin_coroutine_example

import android.app.Application
import com.example.kotlin_coroutine_example.database.UserDao
import com.example.kotlin_coroutine_example.database.UserDatabase
import com.example.kotlin_coroutine_example.repository.UserDatabaseRepository

class MyApplication : Application() {

    private val database by lazy {
        UserDatabase.getInstance(this@MyApplication)
    }

    val repository by lazy {
        UserDatabaseRepository(database.userDao())
    }

}