package com.finpay.wallet.view.app.ui.history

import android.view.View
import lib.finpay.sdk.model.DetailHistoryTransactionModel

interface RecyclerViewClickListener {

    // method yang akan dipanggil di MainActivity
    fun onItemClicked(view: View, negara: DetailHistoryTransactionModel)

}