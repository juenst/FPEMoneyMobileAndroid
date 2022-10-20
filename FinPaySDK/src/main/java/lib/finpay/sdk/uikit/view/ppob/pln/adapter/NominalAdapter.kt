package lib.finpay.sdk.uikit.view.ppob.pln.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.DetailProductModel
import lib.finpay.sdk.uikit.utilities.TextUtils

class NominalAdapter(var mCtx: Context, var resource: Int, var items: MutableList<String>): ArrayAdapter<String>(mCtx , resource , items ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(resource , null )
        var txtNominalToken: TextView = view.findViewById(R.id.txtNominalToken)


        var position : String = items[position]
        txtNominalToken.setText(TextUtils.formatRupiah(position.toDouble()).replace("Rp", ""))

        return view
    }
}