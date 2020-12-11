@file:Suppress("PackageDirectoryMismatch")

package androidx.appcompat.app

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.view.ActionMode
import androidx.appcompat.widget.Toolbar

/**
 * A [AppCompatDelegate] which delegates all calls to it to its [baseDelegate]. Can be subclassed
 * to modify behavior without changing the original [AppCompatDelegate].
 *
 * @param baseDelegate the [AppCompatDelegate] which all calls will be delegated to
 * @param wrapContext a function to wrap the context after [AppCompatDelegate.attachBaseContext2].
 */
open class AppCompatDelegateWrapper @JvmOverloads constructor(
        private val baseDelegate: AppCompatDelegate,
        private val wrapContext: ((baseContext: Context) -> Context)? = null
) : AppCompatDelegate() {

    override fun getSupportActionBar(): ActionBar? {
        return baseDelegate.supportActionBar
    }

    override fun setSupportActionBar(toolbar: Toolbar?) {
        baseDelegate.setSupportActionBar(toolbar)
    }

    override fun getMenuInflater(): MenuInflater {
        return baseDelegate.menuInflater
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        baseDelegate.onCreate(savedInstanceState)
        removeActivityDelegate(baseDelegate)
        addActiveDelegate(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        baseDelegate.onPostCreate(savedInstanceState)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        baseDelegate.onConfigurationChanged(newConfig)
    }

    override fun onStart() {
        baseDelegate.onStart()
    }

    override fun onStop() {
        baseDelegate.onStop()
    }

    override fun onPostResume() {
        baseDelegate.onPostResume()
    }

    override fun setTheme(themeResId: Int) {
        baseDelegate.setTheme(themeResId)
    }

    override fun <T : View?> findViewById(id: Int): T? {
        return baseDelegate.findViewById<T>(id)
    }

    override fun setContentView(v: View?) {
        baseDelegate.setContentView(v)
    }

    override fun setContentView(resId: Int) {
        baseDelegate.setContentView(resId)
    }

    override fun setContentView(v: View?, lp: ViewGroup.LayoutParams?) {
        baseDelegate.setContentView(v, lp)
    }

    override fun addContentView(v: View?, lp: ViewGroup.LayoutParams?) {
        baseDelegate.addContentView(v, lp)
    }

    override fun attachBaseContext2(context: Context): Context {
        return wrap(baseDelegate.attachBaseContext2(super.attachBaseContext2(context)))
    }

    override fun setTitle(title: CharSequence?) {
        baseDelegate.setTitle(title)
    }

    override fun invalidateOptionsMenu() {
        baseDelegate.invalidateOptionsMenu()
    }

    override fun onDestroy() {
        baseDelegate.onDestroy()
        removeActivityDelegate(this)
    }

    override fun getDrawerToggleDelegate(): ActionBarDrawerToggle.Delegate? {
        return baseDelegate.drawerToggleDelegate
    }

    override fun requestWindowFeature(featureId: Int): Boolean {
        return baseDelegate.requestWindowFeature(featureId)
    }

    override fun hasWindowFeature(featureId: Int): Boolean {
        return baseDelegate.hasWindowFeature(featureId)
    }

    override fun startSupportActionMode(callback: ActionMode.Callback): ActionMode? {
        return baseDelegate.startSupportActionMode(callback)
    }

    override fun installViewFactory() {
        baseDelegate.installViewFactory()
    }

    override fun createView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        return baseDelegate.createView(parent, name, context, attrs)
    }

    override fun setHandleNativeActionModesEnabled(enabled: Boolean) {
        baseDelegate.isHandleNativeActionModesEnabled = enabled
    }

    override fun isHandleNativeActionModesEnabled(): Boolean {
        return baseDelegate.isHandleNativeActionModesEnabled
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        return baseDelegate.onSaveInstanceState(outState)
    }

    override fun applyDayNight(): Boolean {
        return baseDelegate.applyDayNight()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun setLocalNightMode(mode: Int) {
        baseDelegate.localNightMode = mode
    }

    override fun getLocalNightMode(): Int {
        return baseDelegate.localNightMode
    }

    private fun wrap(context: Context): Context {
        return wrapContext?.invoke(context) ?: context
    }
}