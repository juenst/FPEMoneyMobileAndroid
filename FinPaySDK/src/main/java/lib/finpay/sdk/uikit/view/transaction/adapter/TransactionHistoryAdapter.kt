package lib.finpay.sdk.uikit.view.transaction.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.DataHistoryTransaction
import lib.finpay.sdk.uikit.utilities.TextUtils

class TransactionHistoryAdapter(var mCtx: Context, var resource: Int, var items: List<DataHistoryTransaction>): ArrayAdapter<DataHistoryTransaction>(mCtx , resource , items ) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

            val view : View = layoutInflater.inflate(resource , null )
            var transactionName : TextView = view.findViewById(R.id.transactionName)
            var transactionDate : TextView = view.findViewById(R.id.transactionDate)
            var transactionAmount : TextView = view.findViewById(R.id.transactionAmount)
            var transactionImages : ImageView = view.findViewById(R.id.transactionImages)
            var transactionType : TextView = view.findViewById(R.id.transactionType)


            var position : DataHistoryTransaction = items[position]

            if(position.type == "pay" || position.type == "AJRI") {
                transactionImages.setImageDrawable(mCtx.resources.getDrawable(R.drawable.ic_history_pay))
            } else if(position.type == "topup") {
                transactionImages.setImageDrawable(mCtx.resources.getDrawable(R.drawable.ic_history_topup))
            } else {
                transactionImages.setImageDrawable(mCtx.resources.getDrawable(R.drawable.ic_history_transfer))
            }
            transactionName.text = position.desc
            transactionDate.text = position.dateTime
            transactionType.text = position.type
            if(position.type == "topup") {
                transactionAmount.text = "+"+TextUtils.formatRupiah(position.value!!.toDouble())
                transactionAmount.setTextColor(Integer.parseUnsignedInt("ff34A853", 16))
            } else {
                transactionAmount.text = "-"+TextUtils.formatRupiah(position.value!!.toDouble())
                transactionAmount.setTextColor(Integer.parseUnsignedInt("ffDF5A2A", 16))
            }

            return view
        }
}