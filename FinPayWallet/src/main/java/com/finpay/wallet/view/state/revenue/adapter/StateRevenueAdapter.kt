package com.finpay.wallet.view.state.revenue.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.finpay.wallet.R
import lib.finpay.sdk.corekit.model.DetailProductModel

class StateRevenueAdapter(
    private var mCtx: Context,
    private var resource: Int,
    private var items: MutableList<DetailProductModel>
) : ArrayAdapter<DetailProductModel>(mCtx, resource, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        val itemStateRevenue: LinearLayout = view.findViewById(R.id.item_menu_state_revenue)
        val itemStateRevenueTitle: TextView = view.findViewById(R.id.item_menu_state_revenue_title)
        val itemStateRevenueDesc: TextView = view.findViewById(R.id.item_menu_state_revenue_desc)
        val itemStateRevenueIcon: ImageView = view.findViewById(R.id.item_menu_state_revenue_icon)


        val position: DetailProductModel = items[position]
        itemStateRevenueTitle.setText(position.getProductDesc())
        return view
    }
}