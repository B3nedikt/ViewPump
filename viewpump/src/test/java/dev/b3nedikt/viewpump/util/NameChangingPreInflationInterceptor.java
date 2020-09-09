package dev.b3nedikt.viewpump.util;

import dev.b3nedikt.viewpump.InflateResult;
import dev.b3nedikt.viewpump.Interceptor;

public class NameChangingPreInflationInterceptor implements Interceptor {

    @Override
    public InflateResult intercept(Chain chain) {
        return chain.proceed(
                chain.request()
                        .toBuilder()
                        .name(AnotherTestView.NAME)
                        .build());
    }
}
