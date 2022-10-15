package lib.finpay.sdk.uikit.view.topup.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.Topup

class TopupAdapter (var mCtx: Context , var resource:Int,var items:List<Topup>):ArrayAdapter<Topup>( mCtx , resource , items ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(resource , null )
        val imageLogo : ImageView = view.findViewById(R.id.iv_icon)
        var title : TextView = view.findViewById(R.id.tv_name)


        var position : Topup = items[position]

        imageLogo.setImageDrawable(mCtx.resources.getDrawable(position.icon))
        title.text = position.name

        return view
    }
}