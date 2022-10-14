// Generated by view binder compiler. Do not edit!
package com.finpay.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView backButtonLogin;

  @NonNull
  public final Button buttonMasuk;

  @NonNull
  public final LinearLayout mainParentLogin;

  @NonNull
  public final EditText phoneNumberField;

  private ActivityLoginBinding(@NonNull LinearLayout rootView, @NonNull ImageView backButtonLogin,
      @NonNull Button buttonMasuk, @NonNull LinearLayout mainParentLogin,
      @NonNull EditText phoneNumberField) {
    this.rootView = rootView;
    this.backButtonLogin = backButtonLogin;
    this.buttonMasuk = buttonMasuk;
    this.mainParentLogin = mainParentLogin;
    this.phoneNumberField = phoneNumberField;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backButtonLogin;
      ImageView backButtonLogin = ViewBindings.findChildViewById(rootView, id);
      if (backButtonLogin == null) {
        break missingId;
      }

      id = R.id.buttonMasuk;
      Button buttonMasuk = ViewBindings.findChildViewById(rootView, id);
      if (buttonMasuk == null) {
        break missingId;
      }

      LinearLayout mainParentLogin = (LinearLayout) rootView;

      id = R.id.phoneNumberField;
      EditText phoneNumberField = ViewBindings.findChildViewById(rootView, id);
      if (phoneNumberField == null) {
        break missingId;
      }

      return new ActivityLoginBinding((LinearLayout) rootView, backButtonLogin, buttonMasuk,
          mainParentLogin, phoneNumberField);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
