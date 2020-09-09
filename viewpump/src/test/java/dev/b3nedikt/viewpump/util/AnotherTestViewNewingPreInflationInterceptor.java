package dev.b3nedikt.viewpump.util;

import dev.b3nedikt.viewpump.InflateRequest;
import dev.b3nedikt.viewpump.InflateResult;
import dev.b3nedikt.viewpump.Interceptor;

public class AnotherTestViewNewingPreInflationInterceptor implements Interceptor {

    @Override
    public InflateResult intercept(Chain chain) {
        InflateRequest request = chain.request();
        if (AnotherTestView.NAME.equals(request.name())) {
            return InflateResult.builder()
                    .view(new AnotherTestView(request.context()))
                    .name(AnotherTestView.NAME)
                    .context(request.context())
                    .attrs(request.attrs())
                    .build();
        } else {
            return chain.proceed(request);
        }
    }
}
