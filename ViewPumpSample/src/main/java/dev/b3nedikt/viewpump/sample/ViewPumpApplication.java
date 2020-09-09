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
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new TextUpdatingInterceptor())
                .addInterceptor(new CustomTextViewInterceptor())
                .build());
    }
}
