package lib.finpay.sdk.uikit.view.topup.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.Pos
import lib.finpay.sdk.corekit.model.Topup

//class TopupGuidancePaymentMethodAdapter {
//}
class TopupSubPaymentAdapter (var mCtx: Context, var resource:Int, var items:List<String>):
    ArrayAdapter<String>( mCtx , resource , items ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(resource , null )
        val indexList : TextView = view.findViewById(R.id.index_sub_payment)
        var title : TextView = view.findViewById(R.id.title_sub_payment)

        indexList.text = (position+1).toString()
        title.text = items[position]


//        var position : Topup = items[position]
//
//        imageLogo.setImageDrawable(mCtx.resources.getDrawable(position.icon))
//        title.text = position.name

        return view
    }
}