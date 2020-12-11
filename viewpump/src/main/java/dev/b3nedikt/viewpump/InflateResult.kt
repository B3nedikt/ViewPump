package dev.b3nedikt.viewpump

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * A request to inflate a view
 *
 * @param view the view that has been created
 * @param name Tag name to be inflated.
 * @param context The context the view is being created in.
 * @param attrs Inflation attributes as specified in XML file.
 */
data class InflateResult(
        val view: View? = null,
        val name: String,
        val context: Context,
        val attrs: AttributeSet? = null
)
