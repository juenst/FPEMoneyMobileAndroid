package com.finpay.wallet.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0018H\u0016R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0019"}, d2 = {"Lcom/finpay/wallet/base/BaseViewModel;", "Landroidx/lifecycle/ViewModel;", "Ldagger/android/HasAndroidInjector;", "()V", "api", "Lcom/finpay/wallet/service/network/Api;", "getApi", "()Lcom/finpay/wallet/service/network/Api;", "setApi", "(Lcom/finpay/wallet/service/network/Api;)V", "dispactchingActivityInjector", "Ldagger/android/DispatchingAndroidInjector;", "", "getDispactchingActivityInjector", "()Ldagger/android/DispatchingAndroidInjector;", "setDispactchingActivityInjector", "(Ldagger/android/DispatchingAndroidInjector;)V", "isLoading", "Landroidx/lifecycle/MutableLiveData;", "", "()Landroidx/lifecycle/MutableLiveData;", "setLoading", "(Landroidx/lifecycle/MutableLiveData;)V", "androidInjector", "Ldagger/android/AndroidInjector;", "FinPayWallet_debug"})
public class BaseViewModel extends androidx.lifecycle.ViewModel implements dagger.android.HasAndroidInjector {
    @javax.inject.Inject()
    public com.finpay.wallet.service.network.Api api;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> isLoading;
    @javax.inject.Inject()
    public dagger.android.DispatchingAndroidInjector<java.lang.Object> dispactchingActivityInjector;
    
    public BaseViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.finpay.wallet.service.network.Api getApi() {
        return null;
    }
    
    public final void setApi(@org.jetbrains.annotations.NotNull()
    com.finpay.wallet.service.network.Api p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    public final void setLoading(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.Boolean> p0) {
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
}