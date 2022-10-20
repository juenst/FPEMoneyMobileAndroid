package lib.finpay.sdk.uikit.view.ppob.pdam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.Country
import lib.finpay.sdk.corekit.model.PDAMRegion


class PDAMAdapter(
    private val listRegion: ArrayList<PDAMRegion>,
    private val onClickListener: (PDAMRegion) -> Unit
) :
    RecyclerView.Adapter<PDAMAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRegionName: TextView = itemView.findViewById(R.id.txtWilayahName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_pdam, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val pdam: PDAMRegion = listRegion[position]
        holder.tvRegionName.text = pdam.name?.uppercase()
        holder.tvRegionName.setOnClickListener {
            onClickListener(pdam)
        }
    }

    override fun getItemCount(): Int = listRegion.size
}