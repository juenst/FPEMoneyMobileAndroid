// Generated by view binder compiler. Do not edit!
package com.finpay.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.finpay.wallet.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentStepperSecondBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnNext;

  @NonNull
  public final CardView contentCard;

  @NonNull
  public final LinearLayout stepperBar;

  private FragmentStepperSecondBinding(@NonNull LinearLayout rootView, @NonNull Button btnNext,
      @NonNull CardView contentCard, @NonNull LinearLayout stepperBar) {
    this.rootView = rootView;
    this.btnNext = btnNext;
    this.contentCard = contentCard;
    this.stepperBar = stepperBar;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentStepperSecondBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentStepperSecondBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_stepper_second, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentStepperSecondBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnNext;
      Button btnNext = ViewBindings.findChildViewById(rootView, id);
      if (btnNext == null) {
        break missingId;
      }

      id = R.id.content_card;
      CardView contentCard = ViewBindings.findChildViewById(rootView, id);
      if (contentCard == null) {
        break missingId;
      }

      id = R.id.stepper_bar;
      LinearLayout stepperBar = ViewBindings.findChildViewById(rootView, id);
      if (stepperBar == null) {
        break missingId;
      }

      return new FragmentStepperSecondBinding((LinearLayout) rootView, btnNext, contentCard,
          stepperBar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
