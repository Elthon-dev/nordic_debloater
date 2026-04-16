package com.creator.nordicdebloater.logic

data class BloatApp(val name: String, val packageName: String)

object Scanner {
    val targetBloat = listOf(
        BloatApp("Oppo App Market", "com.heytap.market"),
        BloatApp("Oppo Theme Store", "com.coloros.themestore"),
        BloatApp("Oppo Browser", "com.coloros.browser"),
        BloatApp("Global Search", "com.oppo.quicksearchbox")
    )
}
