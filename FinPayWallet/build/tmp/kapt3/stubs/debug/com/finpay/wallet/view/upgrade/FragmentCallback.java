package com.finpay.wallet.view.upgrade;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001a\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\b"}, d2 = {"Lcom/finpay/wallet/view/upgrade/FragmentCallback;", "", "onFirstFr", "", "uri", "", "onSecondFr", "firstData", "FinPayWallet_debug"})
public abstract interface FragmentCallback {
    
    public abstract void onFirstFr(@org.jetbrains.annotations.NotNull()
    java.lang.String uri);
    
    public abstract void onSecondFr(@org.jetbrains.annotations.Nullable()
    java.lang.String firstData, @org.jetbrains.annotations.NotNull()
    java.lang.String uri);
}