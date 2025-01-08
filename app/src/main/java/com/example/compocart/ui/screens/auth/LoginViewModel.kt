package com.example.compocart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compocart.data.datasource.remote.response.LoginResponse
import com.example.compocart.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginResponse?>(null)
    val loginState: StateFlow<LoginResponse?> = _loginState

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authRepository.loginUser(username, password)
                if (response.isSuccessful) {
                    _loginState.value = response.body()
                } else {
                    _errorMessage.value = "Login failed: ${response.message()}"
                }
            } catch (e: HttpException) {
                _errorMessage.value = "An error occurred: ${e.message()}"
            } catch (e: Exception) {
                _errorMessage.value = "Unexpected error: ${e.message}"
            }
        }
    }
}