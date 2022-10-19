package com.raj.instaclone.screens.login


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raj.instaclone.R
import com.raj.instaclone.UserSession
import com.raj.instaclone.models.User
import com.raj.instaclone.repositories.UserRepository
import com.raj.instaclone.sealed.DataResponse
import com.raj.instaclone.sealed.Error
import com.raj.instaclone.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val uiState = mutableStateOf<UiState>(UiState.Idle)
    val recentUser = User(
        userId = 1,
        userName = "raj@demon",
        profile = R.drawable.my_profile,
    )
    fun authenticateUser(onAuthenticated: () -> Unit, onAuthenticationFailed: () -> Unit) {
        uiState.value = UiState.Loading
        /** We will use the coroutine so that we don't block our dear : The UiThread */
        viewModelScope.launch {
            userRepository.signInUser(
                email = "rajnarayan8584@gmail.com",
                password = "qwert@001welcome",
            ).let {
                when (it) {
                    is DataResponse.Success -> {
                        it.data?.let {
                            /** Authenticated successfully */
                            uiState.value = UiState.Success
                            UserSession.user = it
                            onAuthenticated()
                        }
                    }
                    is DataResponse.Error -> {
                        /** An error occurred while authenticating */
                        uiState.value = UiState.Error(error = it.error ?: Error.Network)
                        onAuthenticationFailed()
                    }
                }
            }
        }
    }
}