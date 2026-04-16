package com.creator.nordicdebloater.logic
import rikka.shizuku.Shizuku
import android.util.Log

object ShizukuManager {
    fun isAvailable(): Boolean {
        return try { Shizuku.pingBinder() } catch (e: Exception) { false }
    }

    // This is the actual system command to nuke the app
    fun uninstallApp(packageName: String): Boolean {
        if (!isAvailable()) return false
        return try {
            val process = Shizuku.newProcess(arrayOf("sh", "-c", "pm uninstall --user 0 $packageName"), null, null)
            process.waitFor() == 0 // Returns true if successful
        } catch (e: Exception) {
            Log.e("ShizukuManager", "Uninstall failed", e)
            false
        }
    }
}
