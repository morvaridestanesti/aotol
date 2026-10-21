package ir.aotol.app.ui.managers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.concurrent.ConcurrentHashMap
import kotlin.time.Duration.Companion.milliseconds

object LoadingManager {
    private val _map = ConcurrentHashMap<String, String>()

    private val _messages = MutableStateFlow<List<String>>(emptyList())
    val messages: StateFlow<List<String>> get() = _messages.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    @OptIn(FlowPreview::class)
    val visible: StateFlow<Boolean> = _messages
        .map { it.isNotEmpty() }
        .debounce(180L.milliseconds)
        .stateIn(scope, SharingStarted.Eagerly, false)

    fun enterLoading(id: String, message: String? = null) {
        _map[id] = message ?: "Error"
        _messages.value = _map.values.toList()
    }

    fun exitLoading(id: String) {
        _map.remove(id)
        _messages.value = _map.values.toList()
    }
}