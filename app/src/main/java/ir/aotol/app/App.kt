package ir.aotol.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Process
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.yalantis.ucrop.BuildConfig
import ir.aotol.app.database.AppDatabase
import kotlin.system.exitProcess

@SuppressLint("StaticFieldLeak")
class App : Application() {
    companion object {
        val SITE_URL = if (BuildConfig.DEBUG) "http://10.125.213.189:8000" else "https://aotol.ir"
        lateinit var CONTEXT: Context
            private set

        var LANGUAGE by mutableStateOf("en")
        lateinit var DB: AppDatabase

        fun exit() {
            for ((key) in Thread.getAllStackTraces()) {
                key.interrupt()
            }
            Process.killProcess(Process.myPid())
            exitProcess(0)
        }
    }
}