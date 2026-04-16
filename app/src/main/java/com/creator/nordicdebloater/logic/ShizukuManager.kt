package com.creator.nordicdebloater.logic
import rikka.shizuku.Shizuku
object ShizukuManager {
    fun isAvailable() = try { Shizuku.pingBinder() } catch (e: Exception) { false }
    fun uninstall(pkg: String) = try {
        Shizuku.newProcess(arrayOf("sh", "-c", "pm uninstall --user 0 $pkg"), null, null).waitFor() == 0
    } catch (e: Exception) { false }
}
