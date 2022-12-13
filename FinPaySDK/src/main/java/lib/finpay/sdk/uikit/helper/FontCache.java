package lib.finpay.sdk.uikit.helper;


import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by Widi Ramadhan on 10/10/22.
 * <p/>
 */
public class FontCache {

    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeface(String fontname, Context context) {
        Typeface typeface = fontCache.get(fontname);

        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontname);
            } catch (Exception e) {
                return null;
            }

            fontCache.put(fontname, typeface);
        }

        return typeface;
    }
}