package dev.b3nedikt.viewpump.sample

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.ViewPumpAppCompatDelegate
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.app_locale.AppLocale.currentLocale
import dev.b3nedikt.app_locale.AppLocale.desiredLocale
import java.util.Base64
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val appCompatDelegate: AppCompatDelegate by lazy {
        ViewPumpAppCompatDelegate(
            baseDelegate = super.getDelegate(),
            baseContext = this,
            wrapContext = AppLocale::wrap
        )
    }

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
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

        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true
        val unencodedHtml =
            """
                <!DOCTYPE html>
                <html>
                <body>

                <h1>Display a Number Field</h1>

                <form action="/action_page.php">
                  <label for="quantity">Quantity (between 1 and 5):</label>
                  <input type="number" id="quantity" name="quantity" min="1" max="5">
                  <br>
                  <label for="start">Start date:</label>
                  <input type="date" id="start" name="trip-start" value="2018-07-22" min="2018-01-01" max="2018-12-31" />
                  <br>
                  <input type="submit">
                </form>

                </body>
                </html>
            """.trimIndent()
        val encodedHtml = Base64.getEncoder().encodeToString(unencodedHtml.toByteArray())
        webView.loadData(encodedHtml, "text/html", "base64")
    }

    override fun getDelegate(): AppCompatDelegate {
        return appCompatDelegate
    }
}