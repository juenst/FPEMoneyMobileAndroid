package com.finpay.wallet.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0006J\u0016\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\nJ\u0016\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000f\u00a8\u0006\u0011"}, d2 = {"Lcom/finpay/wallet/base/PrefHelper;", "", "()V", "clearDataLogout", "", "getBoolFromShared", "", "enumEntry", "Lcom/finpay/wallet/base/SharedPrefKeys;", "getIntFromShared", "", "setBooleanToShared", "value", "setIntToShared", "setStringToShared", "", "Companion", "FinPayWallet_debug"})
public final class PrefHelper {
    @org.jetbrains.annotations.NotNull()
    public static final com.finpay.wallet.base.PrefHelper.Companion Companion = null;
    private static android.content.SharedPreferences sharedPreferences;
    
    public PrefHelper() {
        super();
    }
    
    public final void setIntToShared(@org.jetbrains.annotations.NotNull()
    com.finpay.wallet.base.SharedPrefKeys enumEntry, int value) {
    }
    
    public final int getIntFromShared(@org.jetbrains.annotations.NotNull()
    com.finpay.wallet.base.SharedPrefKeys enumEntry) {
        return 0;
    }
    
    public final void setStringToShared(@org.jetbrains.annotations.NotNull()
    com.finpay.wallet.base.SharedPrefKeys enumEntry, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void setBooleanToShared(@org.jetbrains.annotations.NotNull()
    com.finpay.wallet.base.SharedPrefKeys enumEntry, boolean value) {
    }
    
    public final boolean getBoolFromShared(@org.jetbrains.annotations.NotNull()
    com.finpay.wallet.base.SharedPrefKeys enumEntry) {
        return false;
    }
    
    public final void clearDataLogout() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/finpay/wallet/base/PrefHelper$Companion;", "", "()V", "sharedPreferences", "Landroid/content/SharedPreferences;", "setSharedPreferences", "", "context", "Landroid/content/Context;", "nameSharedPreferences", "", "modeType", "", "FinPayWallet_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void setSharedPreferences(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        java.lang.String nameSharedPreferences, int modeType) {
        }
    }
}