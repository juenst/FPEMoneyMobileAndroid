package lib.finpay.sdk.uikit.view.ppob.insurance.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import lib.finpay.sdk.R

class PeriodAdapter(var mCtx: Context, var resource: Int, var items: MutableList<String>): ArrayAdapter<String>(mCtx , resource , items ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(resource , null )
        var txtMonth: TextView = view.findViewById(R.id.txtPeriod)


        var position : String = items[position]
        txtMonth.setText(position)

        return view
    }
}