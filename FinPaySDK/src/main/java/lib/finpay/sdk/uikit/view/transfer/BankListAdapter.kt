package lib.finpay.sdk.uikit.view.transfer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lib.finpay.sdk.R
import lib.finpay.sdk.corekit.model.DataBank


class BankListAdapter(
    private val listBank: ArrayList<DataBank>,
    private val onClickListener: (DataBank) -> Unit
) :
    RecyclerView.Adapter<BankListAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBankName: TextView = itemView.findViewById(R.id.txtItemBank)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_bank, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val bank: DataBank = listBank[position]
        holder.tvBankName.text = bank.bank?.uppercase()
        holder.tvBankName.setOnClickListener {
            onClickListener(bank)
        }
    }

    override fun getItemCount(): Int = listBank.size
}