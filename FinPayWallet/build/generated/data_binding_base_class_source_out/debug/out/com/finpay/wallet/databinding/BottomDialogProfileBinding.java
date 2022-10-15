// Generated by view binder compiler. Do not edit!
package com.finpay.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.finpay.wallet.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class BottomDialogProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton imgChangePhoto;

  @NonNull
  public final ImageButton imgQR;

  @NonNull
  public final RelativeLayout rlChangePhoto;

  @NonNull
  public final RelativeLayout rlQR;

  @NonNull
  public final TextView txtChangePhoto;

  @NonNull
  public final TextView txtQR;

  private BottomDialogProfileBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton imgChangePhoto, @NonNull ImageButton imgQR,
      @NonNull RelativeLayout rlChangePhoto, @NonNull RelativeLayout rlQR,
      @NonNull TextView txtChangePhoto, @NonNull TextView txtQR) {
    this.rootView = rootView;
    this.imgChangePhoto = imgChangePhoto;
    this.imgQR = imgQR;
    this.rlChangePhoto = rlChangePhoto;
    this.rlQR = rlQR;
    this.txtChangePhoto = txtChangePhoto;
    this.txtQR = txtQR;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static BottomDialogProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static BottomDialogProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.bottom_dialog_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static BottomDialogProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imgChangePhoto;
      ImageButton imgChangePhoto = ViewBindings.findChildViewById(rootView, id);
      if (imgChangePhoto == null) {
        break missingId;
      }

      id = R.id.imgQR;
      ImageButton imgQR = ViewBindings.findChildViewById(rootView, id);
      if (imgQR == null) {
        break missingId;
      }

      id = R.id.rlChangePhoto;
      RelativeLayout rlChangePhoto = ViewBindings.findChildViewById(rootView, id);
      if (rlChangePhoto == null) {
        break missingId;
      }

      id = R.id.rlQR;
      RelativeLayout rlQR = ViewBindings.findChildViewById(rootView, id);
      if (rlQR == null) {
        break missingId;
      }

      id = R.id.txtChangePhoto;
      TextView txtChangePhoto = ViewBindings.findChildViewById(rootView, id);
      if (txtChangePhoto == null) {
        break missingId;
      }

      id = R.id.txtQR;
      TextView txtQR = ViewBindings.findChildViewById(rootView, id);
      if (txtQR == null) {
        break missingId;
      }

      return new BottomDialogProfileBinding((ConstraintLayout) rootView, imgChangePhoto, imgQR,
          rlChangePhoto, rlQR, txtChangePhoto, txtQR);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
