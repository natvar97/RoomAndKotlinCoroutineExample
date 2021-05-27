package com.example.kotlin_coroutine_example.repository

import androidx.annotation.WorkerThread
import com.example.kotlin_coroutine_example.database.User
import com.example.kotlin_coroutine_example.database.UserDao

class UserDatabaseRepository(
    private val userDao : UserDao
) {

    @WorkerThread
    suspend fun insert(user : User) = userDao.insert(user)

    fun getAllUsers() = userDao.getAllUsers()

    @WorkerThread
    suspend fun delete(user : User) = userDao.delete(user)

}