package lib.finpay.sdk.uikit.view.ppob.voucher.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.DetailProductModel

class VoucherAdapter(var mCtx: Context, var resource: Int, var items: MutableList<String>): ArrayAdapter<String>(mCtx , resource , items ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(resource , null )
        var txtVoucher: TextView = view.findViewById(R.id.txtVoucher)


        var position : String = items[position]
        txtVoucher.setText(position)

        return view
    }
}