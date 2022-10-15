package com.finpay.wallet.view.ppob.voucher.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.finpay.wallet.R
import lib.finpay.sdk.model.DetailHistoryTransactionModel
import lib.finpay.sdk.model.VoucherModel

class VoucherAdapter(var mCtx: Context, var resource: Int, var items: List<VoucherModel>): ArrayAdapter<VoucherModel>(mCtx , resource , items ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(resource , null )
        var voucherName : TextView = view.findViewById(R.id.voucherName)
        var voucherImage : ImageView = view.findViewById(R.id.voucherImage)


        var position : VoucherModel = items[position]

//        if(position.getType() == "pay") {
//            transactionImages.setImageDrawable(mCtx.resources.getDrawable(R.drawable.ic_history_pay))
//        }
        voucherName.text = position.getName()
        voucherImage.setImageDrawable(mCtx.getResources().getDrawable(mCtx.getResources().getIdentifier(position.getImage(), "drawable", mCtx.getPackageName())))

        return view
    }
}