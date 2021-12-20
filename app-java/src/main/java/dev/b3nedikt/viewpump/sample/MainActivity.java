package dev.b3nedikt.viewpump.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.ViewPumpAppCompatDelegate;

import java.util.Locale;

import dev.b3nedikt.app_locale.AppLocale;


public class MainActivity extends AppCompatActivity {

    private AppCompatDelegate appCompatDelegate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if(AppLocale.getCurrentLocale() != Locale.GERMAN) {
                AppLocale.setDesiredLocale(Locale.GERMAN);
            } else {
                AppLocale.setDesiredLocale(Locale.ENGLISH);
            }
            button.setText(getString(R.string.regular_button));
        });
    }

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        if (appCompatDelegate == null) {
            appCompatDelegate = new ViewPumpAppCompatDelegate(
                    super.getDelegate(),
                    this,
                    AppLocale::wrap
            );
        }
        return appCompatDelegate;
    }
}
