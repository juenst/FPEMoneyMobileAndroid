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
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.finpay.wallet.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemHistoryTransactionBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout cardDetail;

  @NonNull
  public final TextView transactionAmount;

  @NonNull
  public final TextView transactionDate;

  @NonNull
  public final ImageView transactionImages;

  @NonNull
  public final TextView transactionName;

  private ItemHistoryTransactionBinding(@NonNull LinearLayout rootView,
      @NonNull LinearLayout cardDetail, @NonNull TextView transactionAmount,
      @NonNull TextView transactionDate, @NonNull ImageView transactionImages,
      @NonNull TextView transactionName) {
    this.rootView = rootView;
    this.cardDetail = cardDetail;
    this.transactionAmount = transactionAmount;
    this.transactionDate = transactionDate;
    this.transactionImages = transactionImages;
    this.transactionName = transactionName;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemHistoryTransactionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemHistoryTransactionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_history_transaction, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemHistoryTransactionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      LinearLayout cardDetail = (LinearLayout) rootView;

      id = R.id.transactionAmount;
      TextView transactionAmount = ViewBindings.findChildViewById(rootView, id);
      if (transactionAmount == null) {
        break missingId;
      }

      id = R.id.transactionDate;
      TextView transactionDate = ViewBindings.findChildViewById(rootView, id);
      if (transactionDate == null) {
        break missingId;
      }

      id = R.id.transactionImages;
      ImageView transactionImages = ViewBindings.findChildViewById(rootView, id);
      if (transactionImages == null) {
        break missingId;
      }

      id = R.id.transactionName;
      TextView transactionName = ViewBindings.findChildViewById(rootView, id);
      if (transactionName == null) {
        break missingId;
      }

      return new ItemHistoryTransactionBinding((LinearLayout) rootView, cardDetail,
          transactionAmount, transactionDate, transactionImages, transactionName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}