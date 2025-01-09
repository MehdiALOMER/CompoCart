package com.example.compocart.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compocart.data.datasource.remote.response.UserProfileResponse
import com.example.compocart.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _userProfile = MutableStateFlow<UserProfileResponse?>(null)
    val userProfile: StateFlow<UserProfileResponse?> get() = _userProfile.asStateFlow()

    fun loadUserProfile() {
        viewModelScope.launch {
            try {
                val profile = profileRepository.fetchUserProfile()
                _userProfile.value = profile
            } catch (e: Exception) {
                _userProfile.value = null

                println("Error loading user profile: ${e.message}")
            }
        }
    }
}