package dev.b3nedikt.viewpump.util;

import dev.b3nedikt.viewpump.InflateResult;
import dev.b3nedikt.viewpump.Interceptor;

public class TestPostInflationInterceptor implements Interceptor {

    @Override
    public InflateResult intercept(Chain chain) {
        InflateResult result = chain.proceed(chain.request());
        if (result.view() instanceof TestView) {
            ((TestView) result.view()).setPostProcessed(true);
        }
        return result;
    }
}
