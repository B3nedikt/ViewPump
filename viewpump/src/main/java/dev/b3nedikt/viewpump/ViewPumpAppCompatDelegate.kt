@file:Suppress("PackageDirectoryMismatch")

package androidx.appcompat.app

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebView
import android.widget.SearchView
import androidx.core.view.LayoutInflaterCompat
import dev.b3nedikt.viewpump.InflateRequest
import dev.b3nedikt.viewpump.InflateResult
import dev.b3nedikt.viewpump.ViewPump
import dev.b3nedikt.viewpump.internal.InterceptorChain
import dev.b3nedikt.viewpump.internal.LegacyLayoutInflater

/**
 * A [AppCompatDelegate] to be used with [ViewPump]
 *
 * @param baseDelegate the [AppCompatDelegate] which will handle all calls which [ViewPump] does
 * not need to overwrite
 * @param baseContext the [Context] which will be used to retrieve the [LayoutInflater] to install
 * [ViewPump]s [LayoutInflater.Factory2]
 * @param wrapContext optional function to wrap the [Context] after it has been attached
 */
class ViewPumpAppCompatDelegate @JvmOverloads constructor(
    private val baseDelegate: AppCompatDelegate,
    private val baseContext: Context,
    private val wrapContext: ((baseContext: Context) -> Context)? = null
) : AppCompatDelegateWrapper(baseDelegate, wrapContext), LayoutInflater.Factory2 {

    override fun installViewFactory() {
        val layoutInflater = LayoutInflater.from(baseContext)
        if (layoutInflater.factory == null) {
            LayoutInflaterCompat.setFactory2(layoutInflater, this)
        } else {
            if (layoutInflater.factory2 !is AppCompatDelegateImpl) {
                Log.i(
                    TAG, "The Activity's LayoutInflater already has a Factory installed"
                            + " so we can not install ViewPump's"
                )
            }
        }
    }

    override fun createView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        return inflate(
            InflateRequest(
                name = name,
                context = context,
                attrs = attrs,
                parent = parent,
                fallbackViewCreator = {
                    var view = runCatching { super.createView(parent, name, context, attrs) }
                        .getOrElse {

                            // We only arrive here if the context passed into this
                            // method does not have a theme. This does only occur for
                            // views which are part of alert dialogs displayed when
                            // interacting with web views on API 22 and below.
                            super.createView(parent, name, baseContext, attrs)
                        }

                    if (view == null) {
                        view = runCatching {
                            createViewCompat(context, name, attrs)
                        }.getOrNull()
                    }

                    // WebViews cannot deal with custom resources, so we need to make
                    // sure we use the unwrapped context here.
                    if (name == "WebView") {
                        view = WebView(baseDelegate.attachBaseContext2(context), attrs)
                    }

                    if (view is WebView && name != "WebView") {
                        view = createCustomWebView(view, context, attrs)
                    }

                    // The framework SearchView needs to be inflated manually,
                    // as it is not inflated by the AppCompatViewInflater
                    if (name == "SearchView") {
                        view = SearchView(context, attrs)
                    }

                    view
                }
            )
        ).view
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        return createView(parent, name, context, attrs)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return createView(null, name, context, attrs)
    }

    private fun inflate(originalRequest: InflateRequest): InflateResult {
        val chain = InterceptorChain(
            interceptors = ViewPump.interceptors ?: emptyList(),
            index = 0,
            request = originalRequest
        )

        return chain.proceed(originalRequest)
    }

    private fun createViewCompat(context: Context, name: String, attrs: AttributeSet): View? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            LayoutInflater.from(context).createView(context, name, null, attrs)
        } else {

            // The old inflater can´t handle ViewStubs
            if (name != "ViewStub") {
                return LegacyLayoutInflater(context).createViewLegacy(context, name, attrs)
            }
            return null
        }
    }

    private fun createCustomWebView(
        view: WebView,
        context: Context,
        attrs: AttributeSet
    ): View? {

        return view.javaClass.constructors
            .find {
                it.parameterTypes.size == 2 &&
                        it.parameterTypes[0] == Context::class.java &&
                        it.parameterTypes[1] == AttributeSet::class.java
            }
            ?.newInstance(
                baseDelegate.attachBaseContext2(context),
                attrs
            ) as View?
    }
}