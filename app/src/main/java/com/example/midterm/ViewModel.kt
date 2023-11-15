package com.example.midterm

import User
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserViewModel : ViewModel() {

    private val userService = MyUserServiceFactory.create()

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val fetchedUsers = withContext(Dispatchers.IO) {
                    userService.getUsers()
                }
                _users.value = fetchedUsers
            } catch (e: Exception) {
                _error.value = "Failed to fetch users: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

object MyUserServiceFactory {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/users"

    fun create(): UserService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(UserService::class.java)
    }
}

