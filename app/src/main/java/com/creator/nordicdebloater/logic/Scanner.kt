package com.creator.nordicdebloater.logic

data class BloatApp(val name: String, val packageName: String)

object Scanner {
    val targetBloat = listOf(
        BloatApp("App Market", "com.heytap.market"),
        BloatApp("Theme Store", "com.coloros.themestore"),
        BloatApp("Browser", "com.coloros.browser"),
        BloatApp("Global Search", "com.oppo.quicksearchbox")
    )
}
