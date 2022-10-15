// Generated by view binder compiler. Do not edit!
package com.finpay.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.finpay.wallet.R;
import com.rd.PageIndicatorView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemCarouselBannerBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnSemuaPromo;

  @NonNull
  public final PageIndicatorView indicator;

  @NonNull
  public final ViewPager viewPagerBanner;

  private ItemCarouselBannerBinding(@NonNull LinearLayout rootView, @NonNull Button btnSemuaPromo,
      @NonNull PageIndicatorView indicator, @NonNull ViewPager viewPagerBanner) {
    this.rootView = rootView;
    this.btnSemuaPromo = btnSemuaPromo;
    this.indicator = indicator;
    this.viewPagerBanner = viewPagerBanner;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemCarouselBannerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemCarouselBannerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_carousel_banner, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemCarouselBannerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnSemuaPromo;
      Button btnSemuaPromo = ViewBindings.findChildViewById(rootView, id);
      if (btnSemuaPromo == null) {
        break missingId;
      }

      id = R.id.indicator;
      PageIndicatorView indicator = ViewBindings.findChildViewById(rootView, id);
      if (indicator == null) {
        break missingId;
      }

      id = R.id.viewPagerBanner;
      ViewPager viewPagerBanner = ViewBindings.findChildViewById(rootView, id);
      if (viewPagerBanner == null) {
        break missingId;
      }

      return new ItemCarouselBannerBinding((LinearLayout) rootView, btnSemuaPromo, indicator,
          viewPagerBanner);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}