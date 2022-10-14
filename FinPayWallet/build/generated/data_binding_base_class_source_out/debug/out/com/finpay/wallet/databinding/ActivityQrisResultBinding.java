// Generated by view binder compiler. Do not edit!
package com.finpay.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.finpay.wallet.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityQrisResultBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView btnBack;

  @NonNull
  public final Button btnNext;

  @NonNull
  public final Button btnRetry;

  @NonNull
  public final ImageView imgResult;

  private ActivityQrisResultBinding(@NonNull LinearLayout rootView, @NonNull ImageView btnBack,
      @NonNull Button btnNext, @NonNull Button btnRetry, @NonNull ImageView imgResult) {
    this.rootView = rootView;
    this.btnBack = btnBack;
    this.btnNext = btnNext;
    this.btnRetry = btnRetry;
    this.imgResult = imgResult;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityQrisResultBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityQrisResultBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_qris_result, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityQrisResultBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBack;
      ImageView btnBack = ViewBindings.findChildViewById(rootView, id);
      if (btnBack == null) {
        break missingId;
      }

      id = R.id.btnNext;
      Button btnNext = ViewBindings.findChildViewById(rootView, id);
      if (btnNext == null) {
        break missingId;
      }

      id = R.id.btnRetry;
      Button btnRetry = ViewBindings.findChildViewById(rootView, id);
      if (btnRetry == null) {
        break missingId;
      }

      id = R.id.imgResult;
      ImageView imgResult = ViewBindings.findChildViewById(rootView, id);
      if (imgResult == null) {
        break missingId;
      }

      return new ActivityQrisResultBinding((LinearLayout) rootView, btnBack, btnNext, btnRetry,
          imgResult);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
