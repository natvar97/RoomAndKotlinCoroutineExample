package com.example.kotlin_coroutine_example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_coroutine_example.database.User
import com.example.kotlin_coroutine_example.repository.UserDatabaseRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDatabaseRepository: UserDatabaseRepository
) : ViewModel() {

    fun insert(user: User) = viewModelScope.launch {
        userDatabaseRepository.insert(user)
    }

    fun getAllUsers() = userDatabaseRepository.getAllUsers()

    fun delete(user: User) = viewModelScope.launch {
        userDatabaseRepository.delete(user)
    }

}