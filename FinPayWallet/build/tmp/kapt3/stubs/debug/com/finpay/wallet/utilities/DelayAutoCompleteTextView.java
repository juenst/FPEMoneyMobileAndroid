package com.finpay.wallet.utilities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u001a\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\bH\u0014J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\bJ\u0010\u0010\u0016\u001a\u00020\u000e2\b\u0010\u0017\u001a\u0004\u0018\u00010\fR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/finpay/wallet/utilities/DelayAutoCompleteTextView;", "Landroidx/appcompat/widget/AppCompatAutoCompleteTextView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mAutoCompleteDelay", "", "mHandler", "Landroid/os/Handler;", "mLoadingIndicator", "Landroid/widget/ProgressBar;", "onFilterComplete", "", "count", "performFiltering", "text", "", "keyCode", "setAutoCompleteDelay", "autoCompleteDelay", "setLoadingIndicator", "progressBar", "Companion", "FinPayWallet_debug"})
public final class DelayAutoCompleteTextView extends androidx.appcompat.widget.AppCompatAutoCompleteTextView {
    private int mAutoCompleteDelay = 750;
    private android.widget.ProgressBar mLoadingIndicator;
    private final android.os.Handler mHandler = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.finpay.wallet.utilities.DelayAutoCompleteTextView.Companion Companion = null;
    private static final int MESSAGE_TEXT_CHANGED = 100;
    private static final int DEFAULT_AUTOCOMPLETE_DELAY = 750;
    
    public DelayAutoCompleteTextView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    public final void setLoadingIndicator(@org.jetbrains.annotations.Nullable()
    android.widget.ProgressBar progressBar) {
    }
    
    public final void setAutoCompleteDelay(int autoCompleteDelay) {
    }
    
    @java.lang.Override()
    protected void performFiltering(@org.jetbrains.annotations.Nullable()
    java.lang.CharSequence text, int keyCode) {
    }
    
    @java.lang.Override()
    public void onFilterComplete(int count) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/finpay/wallet/utilities/DelayAutoCompleteTextView$Companion;", "", "()V", "DEFAULT_AUTOCOMPLETE_DELAY", "", "MESSAGE_TEXT_CHANGED", "FinPayWallet_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}