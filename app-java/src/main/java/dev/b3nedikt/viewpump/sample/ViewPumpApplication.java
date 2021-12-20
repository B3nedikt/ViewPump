package dev.b3nedikt.viewpump.sample;

import android.app.Application;

import dev.b3nedikt.app_locale.AppLocale;
import dev.b3nedikt.app_locale.SharedPrefsAppLocaleRepository;
import dev.b3nedikt.viewpump.ViewPump;

/**
 * For ViewPump.
 */
public class ViewPumpApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppLocale.setAppLocaleRepository(new SharedPrefsAppLocaleRepository(this));

        ViewPump.init(new TextUpdatingInterceptor(), new CustomTextViewInterceptor());
    }
}
