// Generated by view binder compiler. Do not edit!
package com.finpay.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.denzcoskun.imageslider.ImageSlider;
import com.finpay.wallet.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout btnHistoryTransaction;

  @NonNull
  public final LinearLayout btnMore;

  @NonNull
  public final LinearLayout btnTopup;

  @NonNull
  public final LinearLayout btnTransfer;

  @NonNull
  public final LinearLayout btnWallet;

  @NonNull
  public final ImageView icArrowRight;

  @NonNull
  public final ImageView iconVisibility;

  @NonNull
  public final ImageView iconVisibilityOff;

  @NonNull
  public final ImageSlider imageSlider;

  @NonNull
  public final ImageView imgWarning;

  @NonNull
  public final LinearLayout linearPulsaData;

  @NonNull
  public final NestedScrollView scrollView;

  @NonNull
  public final LinearLayout sectionUpgradeAccount;

  @NonNull
  public final TextView tvBeFinpayPremiumBody;

  @NonNull
  public final TextView txtSaldo;

  @NonNull
  public final TextView txtUsername;

  private FragmentHomeBinding(@NonNull ConstraintLayout rootView,
      @NonNull LinearLayout btnHistoryTransaction, @NonNull LinearLayout btnMore,
      @NonNull LinearLayout btnTopup, @NonNull LinearLayout btnTransfer,
      @NonNull LinearLayout btnWallet, @NonNull ImageView icArrowRight,
      @NonNull ImageView iconVisibility, @NonNull ImageView iconVisibilityOff,
      @NonNull ImageSlider imageSlider, @NonNull ImageView imgWarning,
      @NonNull LinearLayout linearPulsaData, @NonNull NestedScrollView scrollView,
      @NonNull LinearLayout sectionUpgradeAccount, @NonNull TextView tvBeFinpayPremiumBody,
      @NonNull TextView txtSaldo, @NonNull TextView txtUsername) {
    this.rootView = rootView;
    this.btnHistoryTransaction = btnHistoryTransaction;
    this.btnMore = btnMore;
    this.btnTopup = btnTopup;
    this.btnTransfer = btnTransfer;
    this.btnWallet = btnWallet;
    this.icArrowRight = icArrowRight;
    this.iconVisibility = iconVisibility;
    this.iconVisibilityOff = iconVisibilityOff;
    this.imageSlider = imageSlider;
    this.imgWarning = imgWarning;
    this.linearPulsaData = linearPulsaData;
    this.scrollView = scrollView;
    this.sectionUpgradeAccount = sectionUpgradeAccount;
    this.tvBeFinpayPremiumBody = tvBeFinpayPremiumBody;
    this.txtSaldo = txtSaldo;
    this.txtUsername = txtUsername;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnHistoryTransaction;
      LinearLayout btnHistoryTransaction = ViewBindings.findChildViewById(rootView, id);
      if (btnHistoryTransaction == null) {
        break missingId;
      }

      id = R.id.btnMore;
      LinearLayout btnMore = ViewBindings.findChildViewById(rootView, id);
      if (btnMore == null) {
        break missingId;
      }

      id = R.id.btnTopup;
      LinearLayout btnTopup = ViewBindings.findChildViewById(rootView, id);
      if (btnTopup == null) {
        break missingId;
      }

      id = R.id.btnTransfer;
      LinearLayout btnTransfer = ViewBindings.findChildViewById(rootView, id);
      if (btnTransfer == null) {
        break missingId;
      }

      id = R.id.btnWallet;
      LinearLayout btnWallet = ViewBindings.findChildViewById(rootView, id);
      if (btnWallet == null) {
        break missingId;
      }

      id = R.id.ic_arrow_right;
      ImageView icArrowRight = ViewBindings.findChildViewById(rootView, id);
      if (icArrowRight == null) {
        break missingId;
      }

      id = R.id.icon_visibility;
      ImageView iconVisibility = ViewBindings.findChildViewById(rootView, id);
      if (iconVisibility == null) {
        break missingId;
      }

      id = R.id.icon_visibility_off;
      ImageView iconVisibilityOff = ViewBindings.findChildViewById(rootView, id);
      if (iconVisibilityOff == null) {
        break missingId;
      }

      id = R.id.imageSlider;
      ImageSlider imageSlider = ViewBindings.findChildViewById(rootView, id);
      if (imageSlider == null) {
        break missingId;
      }

      id = R.id.img_warning;
      ImageView imgWarning = ViewBindings.findChildViewById(rootView, id);
      if (imgWarning == null) {
        break missingId;
      }

      id = R.id.linear_pulsa_data;
      LinearLayout linearPulsaData = ViewBindings.findChildViewById(rootView, id);
      if (linearPulsaData == null) {
        break missingId;
      }

      id = R.id.scrollView;
      NestedScrollView scrollView = ViewBindings.findChildViewById(rootView, id);
      if (scrollView == null) {
        break missingId;
      }

      id = R.id.sectionUpgradeAccount;
      LinearLayout sectionUpgradeAccount = ViewBindings.findChildViewById(rootView, id);
      if (sectionUpgradeAccount == null) {
        break missingId;
      }

      id = R.id.tvBeFinpayPremiumBody;
      TextView tvBeFinpayPremiumBody = ViewBindings.findChildViewById(rootView, id);
      if (tvBeFinpayPremiumBody == null) {
        break missingId;
      }

      id = R.id.txtSaldo;
      TextView txtSaldo = ViewBindings.findChildViewById(rootView, id);
      if (txtSaldo == null) {
        break missingId;
      }

      id = R.id.txtUsername;
      TextView txtUsername = ViewBindings.findChildViewById(rootView, id);
      if (txtUsername == null) {
        break missingId;
      }

      return new FragmentHomeBinding((ConstraintLayout) rootView, btnHistoryTransaction, btnMore,
          btnTopup, btnTransfer, btnWallet, icArrowRight, iconVisibility, iconVisibilityOff,
          imageSlider, imgWarning, linearPulsaData, scrollView, sectionUpgradeAccount,
          tvBeFinpayPremiumBody, txtSaldo, txtUsername);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
