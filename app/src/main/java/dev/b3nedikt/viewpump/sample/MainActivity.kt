package dev.b3nedikt.viewpump.sample

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.ViewPumpAppCompatDelegate
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.app_locale.AppLocale.currentLocale
import dev.b3nedikt.app_locale.AppLocale.desiredLocale
import java.util.*

class MainActivity : AppCompatActivity() {

    private val appCompatDelegate: AppCompatDelegate by lazy {
        ViewPumpAppCompatDelegate(
            baseDelegate = super.getDelegate(),
            baseContext = this,
            wrapContext = AppLocale::wrap
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            desiredLocale = if (currentLocale !== Locale.GERMAN) {
                Locale.GERMAN
            } else {
                Locale.ENGLISH
            }
            button.text = getString(R.string.regular_button)
        }
    }

    override fun getDelegate(): AppCompatDelegate {
        return appCompatDelegate
    }
}