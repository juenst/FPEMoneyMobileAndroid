package com.finpay.wallet.utilities.widget;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0003)*+B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\bJ\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u000eJ\u0014\u0010#\u001a\u00020\u001e2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0%J\u0016\u0010&\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010\'\u001a\u00020(R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00060\u0014R\u00020\u0000X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/finpay/wallet/utilities/widget/SpinnerWithSearch;", "Landroid/widget/LinearLayout;", "contextt", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "adapterAll", "Landroid/widget/SpinnerAdapter;", "containerChipGroup", "containerSearch", "Landroidx/constraintlayout/widget/ConstraintLayout;", "defList", "", "", "defaultChoice", "defaultlbl", "Landroid/widget/TextView;", "lblSelectedFilter", "listAdapter", "Lcom/finpay/wallet/utilities/widget/SpinnerWithSearch$SpinnerWSearchAdapter;", "onItemSelected", "Lcom/finpay/wallet/utilities/widget/SpinnerWithSearch$OnItemSelected;", "rvList", "Landroidx/recyclerview/widget/RecyclerView;", "stateButton", "", "textSearch", "Landroid/widget/EditText;", "getSelectedItem", "", "setAdapter", "adapter", "setDefaultChoice", "text", "setList", "items", "", "setSelectedItem", "position", "", "OnItemClicked", "OnItemSelected", "SpinnerWSearchAdapter", "FinPayWallet_debug"})
public final class SpinnerWithSearch extends android.widget.LinearLayout {
    private final android.content.Context contextt = null;
    private final android.widget.TextView lblSelectedFilter = null;
    private final android.widget.EditText textSearch = null;
    private final androidx.recyclerview.widget.RecyclerView rvList = null;
    private final java.util.List<java.lang.String> defList = null;
    private com.finpay.wallet.utilities.widget.SpinnerWithSearch.SpinnerWSearchAdapter listAdapter;
    private final androidx.constraintlayout.widget.ConstraintLayout containerSearch = null;
    private final android.widget.LinearLayout containerChipGroup = null;
    private boolean stateButton = false;
    private java.lang.String defaultChoice = "";
    private final android.widget.TextView defaultlbl = null;
    private android.widget.SpinnerAdapter adapterAll;
    private com.finpay.wallet.utilities.widget.SpinnerWithSearch.OnItemSelected onItemSelected;
    
    public SpinnerWithSearch(@org.jetbrains.annotations.NotNull()
    android.content.Context contextt, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attributeSet) {
        super(null);
    }
    
    public final void getSelectedItem(@org.jetbrains.annotations.NotNull()
    com.finpay.wallet.utilities.widget.SpinnerWithSearch.OnItemSelected onItemSelected) {
    }
    
    public final void setSelectedItem(@org.jetbrains.annotations.NotNull()
    java.lang.String text, int position) {
    }
    
    public final void setList(@org.jetbrains.annotations.NotNull()
    java.util.Collection<java.lang.String> items) {
    }
    
    public final void setDefaultChoice(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    public final void setAdapter(@org.jetbrains.annotations.NotNull()
    android.widget.SpinnerAdapter adapter) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0082\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0000R\u00020\u00030\u0001:\u0001\u001aB\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\rJ \u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0010\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J \u0010\u0012\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bH\u0016J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\tJ\u0014\u0010\u0018\u001a\u00020\u000f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\rR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/finpay/wallet/utilities/widget/SpinnerWithSearch$SpinnerWSearchAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/finpay/wallet/utilities/widget/SpinnerWithSearch$SpinnerWSearchAdapter$ViewHolder;", "Lcom/finpay/wallet/utilities/widget/SpinnerWithSearch;", "(Lcom/finpay/wallet/utilities/widget/SpinnerWithSearch;)V", "listItem", "", "", "listener", "Lcom/finpay/wallet/utilities/widget/SpinnerWithSearch$OnItemClicked;", "getItemCount", "", "getList", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setItemSelected", "l", "setListItems", "list", "ViewHolder", "FinPayWallet_debug"})
    final class SpinnerWSearchAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.finpay.wallet.utilities.widget.SpinnerWithSearch.SpinnerWSearchAdapter.ViewHolder> {
        private java.util.List<java.lang.String> listItem;
        private com.finpay.wallet.utilities.widget.SpinnerWithSearch.OnItemClicked listener;
        
        public SpinnerWSearchAdapter() {
            super();
        }
        
        public final void setListItems(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> list) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getList() {
            return null;
        }
        
        public final void setItemSelected(@org.jetbrains.annotations.NotNull()
        com.finpay.wallet.utilities.widget.SpinnerWithSearch.OnItemClicked l) {
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.finpay.wallet.utilities.widget.SpinnerWithSearch.SpinnerWSearchAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.finpay.wallet.utilities.widget.SpinnerWithSearch.SpinnerWSearchAdapter.ViewHolder holder, int position) {
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/finpay/wallet/utilities/widget/SpinnerWithSearch$SpinnerWSearchAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemview", "Landroid/view/View;", "(Lcom/finpay/wallet/utilities/widget/SpinnerWithSearch$SpinnerWSearchAdapter;Landroid/view/View;)V", "container", "Landroid/widget/LinearLayout;", "kotlin.jvm.PlatformType", "getContainer", "()Landroid/widget/LinearLayout;", "text", "Landroid/widget/TextView;", "getText", "()Landroid/widget/TextView;", "FinPayWallet_debug"})
        public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            private final android.widget.TextView text = null;
            private final android.widget.LinearLayout container = null;
            
            public ViewHolder(@org.jetbrains.annotations.NotNull()
            android.view.View itemview) {
                super(null);
            }
            
            public final android.widget.TextView getText() {
                return null;
            }
            
            public final android.widget.LinearLayout getContainer() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/finpay/wallet/utilities/widget/SpinnerWithSearch$OnItemClicked;", "", "onItemSelected", "", "text", "", "position", "", "FinPayWallet_debug"})
    public static abstract interface OnItemClicked {
        
        public abstract void onItemSelected(@org.jetbrains.annotations.NotNull()
        java.lang.String text, int position);
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/finpay/wallet/utilities/widget/SpinnerWithSearch$OnItemSelected;", "", "onItemSelected", "", "text", "", "position", "", "FinPayWallet_debug"})
    public static abstract interface OnItemSelected {
        
        public abstract void onItemSelected(@org.jetbrains.annotations.NotNull()
        java.lang.String text, int position);
    }
}