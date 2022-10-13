package com.finpay.wallet.view.pdam.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.finpay.wallet.R
import lib.finpay.sdk.corekit.model.DetailProductModel

class PDAMSearchRegionAdapter(
    private var mCtx: Context,
    private var resource: Int,
    private var items: MutableList<DetailProductModel>
) : ArrayAdapter<DetailProductModel>(mCtx, resource, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        val itemPDAMRegion: LinearLayout = view.findViewById(R.id.item_menu_pdam_search_region)
        val itemPDAMRegionLabel: TextView =
            view.findViewById(R.id.item_menu_pdam_search_region_label)


        val position: DetailProductModel = items[position]
        itemPDAMRegionLabel.setText(position.getProductDesc())
        return view
    }
}