package com.finpay.wallet.view.home.banner.item;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&\u00a8\u0006\u0007"}, d2 = {"Lcom/finpay/wallet/view/home/banner/item/BannerListener;", "", "onBannerClick", "", "promo", "Lcom/finpay/wallet/view/home/banner/model/BannerPromo;", "onSeeAllPromoClick", "FinPayWallet_debug"})
public abstract interface BannerListener {
    
    public abstract void onSeeAllPromoClick();
    
    public abstract void onBannerClick(@org.jetbrains.annotations.NotNull()
    com.finpay.wallet.view.home.banner.model.BannerPromo promo);
}