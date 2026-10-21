package ir.aotol.app.helpers

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import ir.aotol.app.App
import ir.aotol.app.enums.SpKeys
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

object SpHelper {
    val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.CONTEXT)

    fun clear() = sharedPreferences.edit { clear() }

    fun contains(key: SpKeys) = sharedPreferences.contains(key.value)

    inline fun <reified T> get(key: SpKeys, defaultValue: T): T {
        if (!sharedPreferences.contains(key.value)) return defaultValue

        return when (T::class) {
            Boolean::class -> sharedPreferences.getBoolean(key.value, defaultValue as? Boolean ?: false) as T
            Float::class   -> sharedPreferences.getFloat(key.value, defaultValue as? Float ?: 0f) as T
            Int::class     -> sharedPreferences.getInt(key.value, defaultValue as? Int ?: 0) as T
            Long::class    -> sharedPreferences.getLong(key.value, defaultValue as? Long ?: 0L) as T
            String::class  -> sharedPreferences.getString(key.value, defaultValue as? String) as T
            else           -> {
                json.decodeFromString(serializer(), sharedPreferences.getString(key.value, null) ?: return defaultValue)
            }
        }
    }

    inline fun <reified T> put(key: SpKeys, value: T) {
        sharedPreferences.edit {
            when (value) {
                null       -> remove(key)
                is Boolean -> putBoolean(key.value, value)
                is Float   -> putFloat(key.value, value)
                is Int     -> putInt(key.value, value)
                is Long    -> putLong(key.value, value)
                is String  -> putString(key.value, value)
                else       -> putString(key.value, json.encodeToString(value))
            }
        }
    }

    fun remove(key: SpKeys) = sharedPreferences.edit { remove(key.value) }
}