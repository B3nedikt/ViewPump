package dev.b3nedikt.viewpump

import android.content.Context

/**
 * A functional interface, used in the AppCompatDelegateWrapper instead of a function as a
 * parameter to achieve java compatibility without needing to include kotlin in the host project.
 */
fun interface WrapContext {

    /**
     * Performs wrapping of the context
     */
    fun perform(context: Context): Context
}
