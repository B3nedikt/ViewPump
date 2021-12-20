package dev.b3nedikt.viewpump.sample

import androidx.appcompat.widget.Toolbar
import dev.b3nedikt.viewpump.InflateResult
import dev.b3nedikt.viewpump.Interceptor

/**
 * This is an example of a post-inflation interceptor that modifies the properties of a view
 * after it has been created. Here we prefix the text for any view that has been replaced with
 * a custom version by the [CustomTextViewInterceptor].
 */
class TextUpdatingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): InflateResult {
        val result = chain.proceed(chain.request())
        if (result.view is CustomTextView) {
            val textView = result.view as CustomTextView?
            val a = result.context.obtainStyledAttributes(
                result.attrs, intArrayOf(android.R.attr.text)
            )
            try {
                var text = a.getText(0)
                if (text != null && text.isNotEmpty()) {
                    if (text.toString().startsWith("\n")) {
                        text = text.toString().substring(1)
                    }
                    textView!!.text = textView.context.getString(
                        R.string.custom_textview_prefixed_text,
                        text
                    )
                }
            } finally {
                a.recycle()
            }
        }
        if (result.view is Toolbar) {
            val toolbar = result.view as Toolbar?
            toolbar!!.title = "Updated Toolbar Title"
        }
        return result
    }
}