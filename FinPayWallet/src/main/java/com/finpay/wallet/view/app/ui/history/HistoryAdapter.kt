package com.finpay.wallet.view.app.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finpay.wallet.R
import lib.finpay.sdk.model.DetailHistoryTransactionModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*


class HistoryAdapter(private val mList: MutableList<DetailHistoryTransactionModel>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listner: onItemClickListner){
        mListener = listner
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.content_history_transaction, parent, false)

        return ViewHolder(view,mListener)
    }



    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val dataListHistory = mList[position]

        // sets the image to the imageview from our itemHolder class
        // holder.imageView.setImageResource(dataListHistory.image)
        holder.nameTransaction.text = dataListHistory.getDesc()
        holder.amountTransaction.text = "Rp"+dataListHistory.getValues()

        // sets the text to the textview from our itemHolder class
        // holder.textView.text = ItemsViewModel.text
        val str = dataListHistory.getDateTime()

        val pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val localDateTime = LocalDateTime.parse(str, pattern)



        val dates: String = ""+localDateTime.dayOfMonth + " " + localDateTime.month.toString().lowercase().replaceFirstChar {
            it.uppercase()
        }+ " " + localDateTime.year
        val times: String = ""+localDateTime.hour + ":" + localDateTime.minute + ":" + localDateTime.second + " WIB"


        holder.dateTimeTransaction.text = dates + " " + times

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View,listner: onItemClickListner) : RecyclerView.ViewHolder(ItemView) {
        // Set Value to UI
        val nameTransaction: TextView = itemView.findViewById(R.id.nameTransaction)
        val dateTimeTransaction: TextView =  itemView.findViewById(R.id.dateTimeTransaction)
        val amountTransaction: TextView = itemView.findViewById(R.id.amountTransaction)
        /*val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)*/
        init {
            itemView.setOnClickListener{
                listner.onItemClick(adapterPosition)
            }
        }
    }
}