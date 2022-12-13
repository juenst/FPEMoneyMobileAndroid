package lib.finpay.sdk.uikit.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import lib.finpay.sdk.uikit.helper.Utils;

public class TopUpWidget extends LinearLayout {
    private Context mContext;

    // Background Attributes
    private int mDefaultBackgroundColor = Color.BLACK;
    private int mFocusBackgroundColor = 0;
    private int mDisabledBackgroundColor = Color.parseColor("#f6f7f9");
    private int mDisabledTextColor = Color.parseColor("#bec2c9");
    private int mDisabledBorderColor = Color.parseColor("#dddfe2");

    // Text Attributes
    private int mDefaultTextColor = Color.WHITE;
    private int mDefaultIconColor = Color.WHITE;
    private int mTextPosition = 1;
    private int mDefaultTextSize = Utils.spToPx(getContext(), 15);
    private int mDefaultTextGravity = 0x11; // Gravity.CENTER
    private String mText = null;

    // icon
    private ImageView mIconView;
    private TextView mFontIconView;
    private TextView mTextView;

    public TopUpWidget(Context context) {
        super(context);
        this.mContext = context;
    }

    public TopUpWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }
}
