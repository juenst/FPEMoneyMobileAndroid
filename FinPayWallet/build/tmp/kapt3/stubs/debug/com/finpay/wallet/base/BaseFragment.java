package com.finpay.wallet.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012H\u0016J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016R$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0017"}, d2 = {"Lcom/finpay/wallet/base/BaseFragment;", "Ldagger/android/support/DaggerFragment;", "Ldagger/android/HasAndroidInjector;", "()V", "dispactchingActivityInjector", "Ldagger/android/DispatchingAndroidInjector;", "", "getDispactchingActivityInjector", "()Ldagger/android/DispatchingAndroidInjector;", "setDispactchingActivityInjector", "(Ldagger/android/DispatchingAndroidInjector;)V", "prefHelper", "Lcom/finpay/wallet/base/PrefHelper;", "getPrefHelper", "()Lcom/finpay/wallet/base/PrefHelper;", "setPrefHelper", "(Lcom/finpay/wallet/base/PrefHelper;)V", "androidInjector", "Ldagger/android/AndroidInjector;", "loadingDialog", "Landroid/app/Dialog;", "activity", "Landroid/app/Activity;", "FinPayWallet_debug"})
public class BaseFragment extends dagger.android.support.DaggerFragment implements dagger.android.HasAndroidInjector {
    @javax.inject.Inject()
    public dagger.android.DispatchingAndroidInjector<java.lang.Object> dispactchingActivityInjector;
    @javax.inject.Inject()
    public com.finpay.wallet.base.PrefHelper prefHelper;
    
    public BaseFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final dagger.android.DispatchingAndroidInjector<java.lang.Object> getDispactchingActivityInjector() {
        return null;
    }
    
    public final void setDispactchingActivityInjector(@org.jetbrains.annotations.NotNull()
    dagger.android.DispatchingAndroidInjector<java.lang.Object> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public dagger.android.AndroidInjector<java.lang.Object> androidInjector() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.finpay.wallet.base.PrefHelper getPrefHelper() {
        return null;
    }
    
    public final void setPrefHelper(@org.jetbrains.annotations.NotNull()
    com.finpay.wallet.base.PrefHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Dialog loadingDialog(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return null;
    }
}