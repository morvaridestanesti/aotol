package ir.aotol.app

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

object NavigationManager {
    private val _navigationEvent = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val navigationEvent: SharedFlow<String> = _navigationEvent.asSharedFlow()

    private val _signOutEvent = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val signOutEvent: SharedFlow<Unit> = _signOutEvent.asSharedFlow()

    fun navigate(route: String) {
        _navigationEvent.tryEmit(route)
    }

    private val _isSessionRenewalFailed = MutableStateFlow(false)
    val isSessionRenewalFailed: StateFlow<Boolean> = _isSessionRenewalFailed.asStateFlow()

    fun signOut() {
        _isSessionRenewalFailed.value = false
        _signOutEvent.tryEmit(Unit)
    }

    fun showSessionRenewal() {
        _isSessionRenewalFailed.value = true
    }

    fun hideSessionRenewal() {
        _isSessionRenewalFailed.value = false
    }
}
