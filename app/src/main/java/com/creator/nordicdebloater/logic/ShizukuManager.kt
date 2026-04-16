package com.creator.nordicdebloater.logic
import rikka.shizuku.Shizuku

object ShizukuManager {
    fun isAvailable(): Boolean {
        return try { Shizuku.pingBinder() } catch (e: Exception) { false }
    }
}
