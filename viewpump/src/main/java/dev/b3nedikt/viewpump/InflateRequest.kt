package dev.b3nedikt.viewpump

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * A request to inflate a view
 *
 * @param name Tag name to be inflated.
 * @param context The context the view is being created in .
 * @param attrs Inflation attributes as specified in XML file.
 * @param parent The parent that the created view will be placed in.
 * @param fallbackViewCreator Creates a new view as it would get created without interception
 * if the result of this request does not create a view
 */
data class InflateRequest(
        val name: String,
        val context: Context,
        val attrs: AttributeSet? = null,
        val parent: View? = null,
        val fallbackViewCreator: () -> View?
)