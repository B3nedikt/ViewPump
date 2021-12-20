package dev.b3nedikt.viewpump.sample

import android.content.Context
import android.util.AttributeSet
import android.view.View
import dev.b3nedikt.viewpump.InflateResult
import dev.b3nedikt.viewpump.Interceptor


/**
 * This is an example of a pre-inflation interceptor that returns programmatically instantiated
 * CustomTextViews instead of inflating TextViews.
 */
class CustomTextViewInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): InflateResult {
        val request = chain.request()
        val view = inflateView(
            name = request.name,
            context = request.context,
            attrs = request.attrs
        )
        return if (view != null) {
            InflateResult(
                view = view,
                name = request.name,
                context = request.context,
                attrs = request.attrs
            )
        } else {
            chain.proceed(request)
        }
    }

    private fun inflateView(name: String, context: Context, attrs: AttributeSet?): View? {
        return if ("TextView" == name) {
            CustomTextView(context, attrs)
        } else null
    }
}