package com.example.kotlin_coroutine_example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_coroutine_example.repository.UserDatabaseRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val userDatabaseRepository: UserDatabaseRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userDatabaseRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}