package com.finpay.wallet;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.finpay.wallet.databinding.CustomAlertDialogBindingImpl;
import com.finpay.wallet.databinding.DialogAlertButtonWithImageBindingImpl;
import com.finpay.wallet.databinding.DialogLoadingBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_CUSTOMALERTDIALOG = 1;

  private static final int LAYOUT_DIALOGALERTBUTTONWITHIMAGE = 2;

  private static final int LAYOUT_DIALOGLOADING = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.finpay.wallet.R.layout.custom_alert_dialog, LAYOUT_CUSTOMALERTDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.finpay.wallet.R.layout.dialog_alert_button_with_image, LAYOUT_DIALOGALERTBUTTONWITHIMAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.finpay.wallet.R.layout.dialog_loading, LAYOUT_DIALOGLOADING);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_CUSTOMALERTDIALOG: {
          if ("layout/custom_alert_dialog_0".equals(tag)) {
            return new CustomAlertDialogBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for custom_alert_dialog is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGALERTBUTTONWITHIMAGE: {
          if ("layout/dialog_alert_button_with_image_0".equals(tag)) {
            return new DialogAlertButtonWithImageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_alert_button_with_image is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGLOADING: {
          if ("layout/dialog_loading_0".equals(tag)) {
            return new DialogLoadingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_loading is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(2);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    result.add(new lib.finpay.sdk.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/custom_alert_dialog_0", com.finpay.wallet.R.layout.custom_alert_dialog);
      sKeys.put("layout/dialog_alert_button_with_image_0", com.finpay.wallet.R.layout.dialog_alert_button_with_image);
      sKeys.put("layout/dialog_loading_0", com.finpay.wallet.R.layout.dialog_loading);
    }
  }
}
