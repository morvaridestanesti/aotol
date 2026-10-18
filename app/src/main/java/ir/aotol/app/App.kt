package ir.aotol.app

import android.app.Application
import android.os.Process
import kotlin.system.exitProcess

class App : Application() {
    companion object {
        fun exit() {
            for ((key) in Thread.getAllStackTraces()) {
                key.interrupt()
            }
            Process.killProcess(Process.myPid())
            exitProcess(0)
        }
    }
}