package com.finpay.wallet.service.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u0000 \"*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\"#B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0017\u001a\u00020\u0004H\u00c6\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00018\u0000H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0011JB\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0002H\u00d6\u0003J\t\u0010 \u001a\u00020\tH\u00d6\u0001J\t\u0010!\u001a\u00020\u0007H\u00d6\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006$"}, d2 = {"Lcom/finpay/wallet/service/network/ApiResult;", "T", "", "status", "Lcom/finpay/wallet/service/network/ApiResult$Status;", "data", "message", "", "messageInt", "", "(Lcom/finpay/wallet/service/network/ApiResult$Status;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Integer;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getMessage", "()Ljava/lang/String;", "getMessageInt", "()Ljava/lang/Integer;", "setMessageInt", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getStatus", "()Lcom/finpay/wallet/service/network/ApiResult$Status;", "component1", "component2", "component3", "component4", "copy", "(Lcom/finpay/wallet/service/network/ApiResult$Status;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Integer;)Lcom/finpay/wallet/service/network/ApiResult;", "equals", "", "other", "hashCode", "toString", "Companion", "Status", "FinPayWallet_debug"})
public final class ApiResult<T extends java.lang.Object> {
    @org.jetbrains.annotations.NotNull()
    private final com.finpay.wallet.service.network.ApiResult.Status status = null;
    @org.jetbrains.annotations.Nullable()
    private final T data = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String message = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer messageInt;
    @org.jetbrains.annotations.NotNull()
    public static final com.finpay.wallet.service.network.ApiResult.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.finpay.wallet.service.network.ApiResult<T> copy(@org.jetbrains.annotations.NotNull()
    com.finpay.wallet.service.network.ApiResult.Status status, @org.jetbrains.annotations.Nullable()
    T data, @org.jetbrains.annotations.Nullable()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    java.lang.Integer messageInt) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public ApiResult(@org.jetbrains.annotations.NotNull()
    com.finpay.wallet.service.network.ApiResult.Status status, @org.jetbrains.annotations.Nullable()
    T data, @org.jetbrains.annotations.Nullable()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    java.lang.Integer messageInt) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.finpay.wallet.service.network.ApiResult.Status component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.finpay.wallet.service.network.ApiResult.Status getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final T component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final T getData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getMessageInt() {
        return null;
    }
    
    public final void setMessageInt(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/finpay/wallet/service/network/ApiResult$Status;", "", "(Ljava/lang/String;I)V", "SUCCESS", "ERROR", "LOADING", "FinPayWallet_debug"})
    public static enum Status {
        /*public static final*/ SUCCESS /* = new SUCCESS() */,
        /*public static final*/ ERROR /* = new ERROR() */,
        /*public static final*/ LOADING /* = new LOADING() */;
        
        Status() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J+\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u0001H\u0005\u00a2\u0006\u0002\u0010\fJ#\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u0001H\u0005\u00a2\u0006\u0002\u0010\u000eJ\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u000b\u001a\u0002H\u0005\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u0010"}, d2 = {"Lcom/finpay/wallet/service/network/ApiResult$Companion;", "", "()V", "error", "Lcom/finpay/wallet/service/network/ApiResult;", "T", "message", "", "errorInt", "messageInt", "", "data", "(ILjava/lang/Object;)Lcom/finpay/wallet/service/network/ApiResult;", "loading", "(Ljava/lang/Object;)Lcom/finpay/wallet/service/network/ApiResult;", "success", "FinPayWallet_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.finpay.wallet.service.network.ApiResult<T> success(T data) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.finpay.wallet.service.network.ApiResult<T> error(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.finpay.wallet.service.network.ApiResult<T> loading(@org.jetbrains.annotations.Nullable()
        T data) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final <T extends java.lang.Object>com.finpay.wallet.service.network.ApiResult<T> errorInt(int messageInt, @org.jetbrains.annotations.Nullable()
        T data) {
            return null;
        }
    }
}