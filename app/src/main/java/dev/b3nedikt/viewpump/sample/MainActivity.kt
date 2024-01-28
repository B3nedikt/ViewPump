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

                    <h1>HTML which shows native views</h1>

                    <form action="/action_page.php">
                        <label for="quantity">Number Picker:</label>
                        <input type="number" id="numberPicker" name="quantity">
                        <br>
                        <label for="start">Date Picker</label>
                        <input type="date" id="datePicker" name="trip-start" value="2018-07-22" min="2018-01-01" max="2018-12-31" />
                        <br>
                        <button id="alertDialog">Alert Dialog</button>

                        <dialog id="favDialog">
                            <form method="dialog">
                                <section>
                                    <p><label for="favAnimal">Favorite animal:</label>
                                        <select id="favAnimal">
                                            <option>Brine shrimp</option>
                                            <option>Red panda</option>
                                            <option>Spider monkey</option>
                                        </select>
                                    </p>
                                </section>
                                <menu>
                                    <button id="cancel" type="reset">Cancel</button>
                                    <button type="submit">Confirm</button>
                                </menu>
                            </form>
                        </dialog>

                        <script>
                            (function () {
                                var updateButton = document.getElementById('alertDialog');
                                var cancelButton = document.getElementById('cancel');
                                var favDialog = document.getElementById('favDialog');

                                // Update button opens a modal dialog
                                updateButton.addEventListener('click', function () {
                                    favDialog.showModal();
                                });

                                // Form cancel button closes the dialog box
                                cancelButton.addEventListener('click', function () {
                                    favDialog.close();
                                });

                            })();
                        </script>

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