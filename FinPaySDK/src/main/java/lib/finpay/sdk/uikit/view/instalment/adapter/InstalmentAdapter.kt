package lib.finpay.sdk.uikit.view.instalment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.DetailProductModel

class InstalmentAdapter(var mCtx: Context, var resource: Int, var items: MutableList<DetailProductModel>): ArrayAdapter<DetailProductModel>(mCtx , resource , items ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(resource , null )
        var menuInstalment: LinearLayout = view.findViewById(R.id.itemMenuInstalment)
        var textInstalment: TextView = view.findViewById(R.id.selectedMenuInstalment)
//        var transactionName : TextView = view.findViewById(R.id.transactionName)
//        var transactionDate : TextView = view.findViewById(R.id.transactionDate)
//        var transactionAmount : TextView = view.findViewById(R.id.transactionAmount)
//        var transactionImages : ImageView = view.findViewById(R.id.transactionImages)


        var position : DetailProductModel = items[position]
        textInstalment.setText(position.getProductDesc())
        return view
    }
}