package lib.finpay.sdk.uikit.view.ppob.pulsa_data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.DataHistoryTransaction
import lib.finpay.sdk.corekit.model.DataSubProduct
import lib.finpay.sdk.uikit.utilities.TextUtils
import lib.finpay.sdk.uikit.utilities.Utils

class PulsaDataAdapter(var mCtx: Context, var resource: Int, var items: List<DataSubProduct>): ArrayAdapter<DataSubProduct>(mCtx , resource , items ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(resource , null )
        var txtDenom : TextView = view.findViewById(R.id.txtDenom)
        var txtDesc : TextView = view.findViewById(R.id.txtDesc)
        var txtPrice : TextView = view.findViewById(R.id.txtPrice)


        var position : DataSubProduct = items[position]

        var denom: String = ""
        var price: String = ""
        var subProductCode: String = ""
        var info: String = ""
        var type: String = ""

        var splitArrayDenom = position.denom.split("/")
        denom = splitArrayDenom[0]
        price = splitArrayDenom[1]

        var splitArrayProvider = position.provider.split("/")
        subProductCode = splitArrayProvider[1]

        if(position.info.contains("Pulsa")) {
            info = "Pulsa "+splitArrayProvider[0]+" "+TextUtils.formatRupiah(denom.toDouble())
        } else {
            info = "Paket Data "+splitArrayProvider[0]+" "+TextUtils.formatRupiah(denom.toDouble())
        }

        txtDenom.text = Utils.getFormatedNumber(denom.toLong())
        txtDesc.text = info
        txtPrice.text = TextUtils.formatRupiah(price.toDouble())

        return view
    }
}