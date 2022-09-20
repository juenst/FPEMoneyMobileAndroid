package com.finpay.wallet.utilities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0006R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u0015X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0006R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u00020\u001cX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u001cX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0014\u0010!\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006\u00a8\u0006#"}, d2 = {"Lcom/finpay/wallet/utilities/ConstValue;", "", "()V", "ASCENDING", "", "getASCENDING", "()Ljava/lang/String;", "CURRENCY", "DESCENDING", "getDESCENDING", "EDIT_MODE", "getEDIT_MODE", "FARMER", "FARMER_ID", "getFARMER_ID", "GARDEN_ID", "getGARDEN_ID", "JOB_VACANCY_SHOW_TIME", "getJOB_VACANCY_SHOW_TIME", "KOPERASI", "MAP_ZOOM_VALUE", "", "getMAP_ZOOM_VALUE", "()F", "PARSE_DATE_LARAVEL", "getPARSE_DATE_LARAVEL", "SUPPLIER", "notificationKoperasi", "", "getNotificationKoperasi", "()I", "notificationPetani", "getNotificationPetani", "sharedPreferencesName", "getSharedPreferencesName", "FinPayWallet_debug"})
public final class ConstValue {
    @org.jetbrains.annotations.NotNull()
    public static final com.finpay.wallet.utilities.ConstValue INSTANCE = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FARMER = "PTN";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUPPLIER = "PKS";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KOPERASI = "KPR";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String sharedPreferencesName = "FinpaySharedPref";
    private static final int notificationKoperasi = 2;
    private static final int notificationPetani = 3;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CURRENCY = "Rp";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String JOB_VACANCY_SHOW_TIME = "EEEE, d MMM yyyy HH:mm Z";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PARSE_DATE_LARAVEL = "yyyy-MM-dd hh:mm:ss";
    private static final float MAP_ZOOM_VALUE = 15.0F;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String GARDEN_ID = "gardenIdKey";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String FARMER_ID = "farmerIdKey";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String EDIT_MODE = "editModeKey";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ASCENDING = "asc";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DESCENDING = "desc";
    
    private ConstValue() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSharedPreferencesName() {
        return null;
    }
    
    public final int getNotificationKoperasi() {
        return 0;
    }
    
    public final int getNotificationPetani() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJOB_VACANCY_SHOW_TIME() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPARSE_DATE_LARAVEL() {
        return null;
    }
    
    public final float getMAP_ZOOM_VALUE() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGARDEN_ID() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFARMER_ID() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEDIT_MODE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getASCENDING() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDESCENDING() {
        return null;
    }
}