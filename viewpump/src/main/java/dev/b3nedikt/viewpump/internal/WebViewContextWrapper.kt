package dev.b3nedikt.viewpump.internal

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources

internal class WebViewContextWrapper(baseContext: Context) : ContextWrapper(baseContext) {

    override fun getResources(): Resources {
        return applicationContext.resources
    }
}