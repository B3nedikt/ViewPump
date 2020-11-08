package dev.b3nedikt.viewpump.sample;

import android.app.Application;

import dev.b3nedikt.viewpump.ViewPump;

/**
 * For ViewPump.
 */
public class ViewPumpApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ViewPump.init(new TextUpdatingInterceptor(), new CustomTextViewInterceptor());
    }
}
