@file:JvmName("-FallbackViewCreationInterceptor")
package dev.b3nedikt.viewpump.internal

import dev.b3nedikt.viewpump.InflateResult
import dev.b3nedikt.viewpump.Interceptor
import dev.b3nedikt.viewpump.Interceptor.Chain

@Suppress("ClassName")
internal class `-FallbackViewCreationInterceptor` : Interceptor {

  override fun intercept(chain: Chain): InflateResult {
    val request = chain.request()
    val viewCreator = request.fallbackViewCreator
    val fallbackView = viewCreator.onCreateView(request.parent, request.name, request.context,
        request.attrs)

    return InflateResult(
        view = fallbackView,
        name = fallbackView?.javaClass?.name ?: request.name,
        context = request.context,
        attrs = request.attrs
    )
  }
}
