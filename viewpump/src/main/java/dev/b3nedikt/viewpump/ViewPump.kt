package dev.b3nedikt.viewpump

/**
 * ViewPump is a library to intercept view inflation
 */
object ViewPump {

    internal var interceptors: List<Interceptor>? = null

    /**
     * Initialize ViewPump with the provided [interceptors], should ideally be called in your
     * application class.
     */
    @JvmStatic
    fun init(vararg interceptors: Interceptor) {
        this.interceptors = interceptors.toList()
    }
}