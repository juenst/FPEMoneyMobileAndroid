package lib.finpay.sdk.uikit.view.transaction.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.DetailHistoryTransactionModel

class TransactionHistoryAdapter(var mCtx: Context, var resource: Int, var items: List<DetailHistoryTransactionModel>): ArrayAdapter<DetailHistoryTransactionModel>(mCtx , resource , items ) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

            val view : View = layoutInflater.inflate(resource , null )
            var transactionName : TextView = view.findViewById(R.id.transactionName)
            var transactionDate : TextView = view.findViewById(R.id.transactionDate)
            var transactionAmount : TextView = view.findViewById(R.id.transactionAmount)
            var transactionImages : ImageView = view.findViewById(R.id.transactionImages)


            var position : DetailHistoryTransactionModel = items[position]

            println("get Desc")
            println(position.getDesc())
            if(position.getType() == "pay") {
                transactionImages.setImageDrawable(mCtx.resources.getDrawable(R.drawable.ic_history_pay))
            }
            transactionName.text = position.getDesc()
            transactionDate.text = position.getDateTime()
            transactionAmount.text = position.getValues()

            return view
        }
}