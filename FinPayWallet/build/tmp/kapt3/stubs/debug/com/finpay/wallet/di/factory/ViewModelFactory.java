package com.finpay.wallet.di.factory;

import java.lang.System;

@kotlin.Suppress(names = {"UNCHECKED_CAST"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B.\b\u0007\u0012%\u0010\u0002\u001a!\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00050\u0006\u00a2\u0006\u0002\b\u00070\u0003\u00a2\u0006\u0002\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR-\u0010\u0002\u001a!\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00050\u0006\u00a2\u0006\u0002\b\u00070\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/finpay/wallet/di/factory/ViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "viewModelsMap", "", "Ljava/lang/Class;", "Landroidx/lifecycle/ViewModel;", "Ljavax/inject/Provider;", "Lkotlin/jvm/JvmSuppressWildcards;", "(Ljava/util/Map;)V", "api", "Lcom/finpay/wallet/service/network/Api;", "getApi", "()Lcom/finpay/wallet/service/network/Api;", "setApi", "(Lcom/finpay/wallet/service/network/Api;)V", "FinPayWallet_debug"})
public final class ViewModelFactory implements androidx.lifecycle.ViewModelProvider.Factory {
    private final java.util.Map<java.lang.Class<? extends androidx.lifecycle.ViewModel>, javax.inject.Provider<androidx.lifecycle.ViewModel>> viewModelsMap = null;
    @javax.inject.Inject()
    public com.finpay.wallet.service.network.Api api;
    
    @javax.inject.Inject()
    public ViewModelFactory(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.Class<? extends androidx.lifecycle.ViewModel>, javax.inject.Provider<androidx.lifecycle.ViewModel>> viewModelsMap) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.finpay.wallet.service.network.Api getApi() {
        return null;
    }
    
    public final void setApi(@org.jetbrains.annotations.NotNull()
    com.finpay.wallet.service.network.Api p0) {
    }
}