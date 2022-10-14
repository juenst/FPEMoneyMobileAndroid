// Generated by view binder compiler. Do not edit!
package com.finpay.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.finpay.wallet.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemBpjsBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout itemMenuBPJS;

  @NonNull
  public final TextView selectedMenuBPJS;

  private ItemBpjsBinding(@NonNull LinearLayout rootView, @NonNull LinearLayout itemMenuBPJS,
      @NonNull TextView selectedMenuBPJS) {
    this.rootView = rootView;
    this.itemMenuBPJS = itemMenuBPJS;
    this.selectedMenuBPJS = selectedMenuBPJS;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemBpjsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemBpjsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_bpjs, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemBpjsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.itemMenuBPJS;
      LinearLayout itemMenuBPJS = ViewBindings.findChildViewById(rootView, id);
      if (itemMenuBPJS == null) {
        break missingId;
      }

      id = R.id.selectedMenuBPJS;
      TextView selectedMenuBPJS = ViewBindings.findChildViewById(rootView, id);
      if (selectedMenuBPJS == null) {
        break missingId;
      }

      return new ItemBpjsBinding((LinearLayout) rootView, itemMenuBPJS, selectedMenuBPJS);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
