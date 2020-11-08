package dev.b3nedikt.viewpump.sample;

import android.content.res.TypedArray;

import org.jetbrains.annotations.NotNull;

import dev.b3nedikt.viewpump.InflateResult;
import dev.b3nedikt.viewpump.Interceptor;
import io.github.inflationx.viewpump.sample.R;

/**
 * This is an example of a post-inflation interceptor that modifies the properties of a view
 * after it has been created. Here we prefix the text for any view that has been replaced with
 * a custom version by the {@link CustomTextViewInterceptor}.
 */
public class TextUpdatingInterceptor implements Interceptor {

    @NotNull
    @Override
    public InflateResult intercept(Chain chain) {
        InflateResult result = chain.proceed(chain.request());
        if (result.getView() instanceof CustomTextView) {
            CustomTextView textView = (CustomTextView) result.getView();

            TypedArray a = result.getContext().obtainStyledAttributes(
                    result.getAttrs(), new int[]{android.R.attr.text}
            );

            try {
                CharSequence text = a.getText(0);
                if (text != null && text.length() > 0) {
                    if (text.toString().startsWith("\n")) {
                        text = text.toString().substring(1);
                    }
                    textView.setText(textView.getContext().getString(R.string.custom_textview_prefixed_text, text));
                }
            } finally {
                a.recycle();
            }
        }
        return result;
    }
}
