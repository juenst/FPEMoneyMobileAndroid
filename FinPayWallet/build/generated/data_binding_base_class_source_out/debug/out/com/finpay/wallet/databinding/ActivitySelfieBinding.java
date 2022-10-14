// Generated by view binder compiler. Do not edit!
package com.finpay.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.finpay.wallet.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySelfieBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView btnBack;

  @NonNull
  public final ImageView btnFlash;

  @NonNull
  public final CardView btnTakePic;

  @NonNull
  public final PreviewView previewView;

  @NonNull
  public final ImageView previewViewOverlay;

  private ActivitySelfieBinding(@NonNull LinearLayout rootView, @NonNull ImageView btnBack,
      @NonNull ImageView btnFlash, @NonNull CardView btnTakePic, @NonNull PreviewView previewView,
      @NonNull ImageView previewViewOverlay) {
    this.rootView = rootView;
    this.btnBack = btnBack;
    this.btnFlash = btnFlash;
    this.btnTakePic = btnTakePic;
    this.previewView = previewView;
    this.previewViewOverlay = previewViewOverlay;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySelfieBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySelfieBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_selfie, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySelfieBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBack;
      ImageView btnBack = ViewBindings.findChildViewById(rootView, id);
      if (btnBack == null) {
        break missingId;
      }

      id = R.id.btnFlash;
      ImageView btnFlash = ViewBindings.findChildViewById(rootView, id);
      if (btnFlash == null) {
        break missingId;
      }

      id = R.id.btn_take_pic;
      CardView btnTakePic = ViewBindings.findChildViewById(rootView, id);
      if (btnTakePic == null) {
        break missingId;
      }

      id = R.id.preview_view;
      PreviewView previewView = ViewBindings.findChildViewById(rootView, id);
      if (previewView == null) {
        break missingId;
      }

      id = R.id.preview_view_overlay;
      ImageView previewViewOverlay = ViewBindings.findChildViewById(rootView, id);
      if (previewViewOverlay == null) {
        break missingId;
      }

      return new ActivitySelfieBinding((LinearLayout) rootView, btnBack, btnFlash, btnTakePic,
          previewView, previewViewOverlay);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
