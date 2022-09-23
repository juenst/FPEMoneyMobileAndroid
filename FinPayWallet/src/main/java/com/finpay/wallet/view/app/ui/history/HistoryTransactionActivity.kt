package com.finpay.wallet.view.app.ui.history

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finpay.wallet.R
import com.google.gson.Gson
import lib.finpay.sdk.FinPaySDK
import lib.finpay.sdk.model.DetailHistoryTransactionModel
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat


class HistoryTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_transaction)
        supportActionBar!!.hide()
        val userName: String = "MT77764DKM83N"
        val password: String = "YJV3AM0y"
        val secretKey: String = "daYumnMb"

//        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerViewHistory)
        val loadingBar = findViewById<ProgressBar>(R.id.loadingBar)
        val historyContent = findViewById<LinearLayout>(R.id.historyContent)
        val emptyHistory = findViewById<LinearLayout>(R.id.emptyHistory)
        val backButton = findViewById<ImageView>(R.id.backButton)

        val filter7Days = findViewById<androidx.cardview.widget.CardView>(R.id.filter7Days)
        val textTitleFilter7Days = findViewById<TextView>(R.id.titlefilter7Days)
        val textDescFilter7Days = findViewById<TextView>(R.id.descfilter7Days)
        filter7Days.setCardBackgroundColor(getResources().getColor(R.color.colorButton))
        textTitleFilter7Days.setTextColor(Color.WHITE)
        textDescFilter7Days.setTextColor(Color.WHITE)

        val filter30Days = findViewById<androidx.cardview.widget.CardView>(R.id.filter30Days)
        filter30Days.setCardBackgroundColor(Color.WHITE)
        val textTitleFilter30Days = findViewById<TextView>(R.id.titlefilter30Days)
        val textDescFilter30Days = findViewById<TextView>(R.id.descfilter30Days)
        textTitleFilter30Days.setTextColor(Color.BLACK)
        textDescFilter30Days.setTextColor(Color.BLACK)

        val filter3Months = findViewById<androidx.cardview.widget.CardView>(R.id.filter3Months)
        filter3Months.setCardBackgroundColor(Color.WHITE)
        val textTitleFilter3Months = findViewById<TextView>(R.id.titlefilter3Months)
        val textDescFilter3Months = findViewById<TextView>(R.id.descfilter3Months)
        textTitleFilter3Months.setTextColor(Color.BLACK)
        textDescFilter3Months.setTextColor(Color.BLACK)

        backButton.setOnClickListener {
            onBackPressed()
        }

        var indexFilter:Int = -1;

//
//        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        var historyListFilter: MutableList<DetailHistoryTransactionModel> = ArrayList()
        var historyListAll: MutableList<DetailHistoryTransactionModel> = ArrayList()


        FinPaySDK().getHistoryTransaction(
            userName,
            password,
            secretKey,
            "TRX1234567890",
            "083815613839",
            onSuccess = {
                    listData ->
                println("List Data : " + listData.toString())

                if(listData.isNotEmpty()){
                    historyListFilter = listData
                    historyListAll = listData

                    loadingBar.visibility = View.GONE
                    historyContent.visibility=View.VISIBLE
                    emptyHistory.visibility=View.GONE

                    historyListFilter = historyListAll.filter { dataListHistory ->

                        val str = dataListHistory.getDateTime()

                        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                        val currentDate = sdf.format(java.util.Date())

                        val pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

                        val date = LocalDate.parse(currentDate,pattern)
                        val dateMinus7Days = date.minusDays(8)
                        println("dateMinus7Days " + dateMinus7Days)

                        val dataDate = LocalDate.parse(str,pattern)

                        dataDate.isBefore(date).and(dataDate.isAfter(dateMinus7Days))

                    } as MutableList<DetailHistoryTransactionModel>
                    println("Button Filter 7 Days Change value")
                    val adapter = HistoryAdapter(historyListFilter)

                    recyclerview.adapter = adapter
                    adapter.setOnItemClickListener(object : HistoryAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            println("Data di klik $position")
                            intenDetailHistory(
                                historyListFilter[position]
                            )
                        }

                    })
                }else{
                    emptyHistory.visibility=View.VISIBLE
                    historyContent.visibility=View.GONE
                }
            }
        )

        filter7Days.setOnClickListener(){
            println("Button Filter 7 Days Clicked $indexFilter")
            if (indexFilter != 0){
                indexFilter = 0
                filter7Days.setCardBackgroundColor(getResources().getColor(R.color.colorButton))
                textTitleFilter7Days.setTextColor(Color.WHITE)
                textDescFilter7Days.setTextColor(Color.WHITE)

                filter30Days.setCardBackgroundColor(Color.WHITE)
                textTitleFilter30Days.setTextColor(Color.BLACK)
                textDescFilter30Days.setTextColor(Color.BLACK)

                filter3Months.setCardBackgroundColor(Color.WHITE)
                textTitleFilter3Months.setTextColor(Color.BLACK)
                textDescFilter3Months.setTextColor(Color.BLACK)


                println("Button Filter 7 Days Change Color $indexFilter")
                if (historyListAll.isNotEmpty()){
                    historyListFilter = historyListAll.filter { dataListHistory ->

                        val str = dataListHistory.getDateTime()

                        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                        val currentDate = sdf.format(java.util.Date())

                        val pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

                        val date = LocalDate.parse(currentDate,pattern)
                        val dateMinus7Days = date.minusDays(8)
                        println("dateMinus7Days " + dateMinus7Days)

                        val dataDate = LocalDate.parse(str,pattern)

                        dataDate.isBefore(date).and(dataDate.isAfter(dateMinus7Days))

                    } as MutableList<DetailHistoryTransactionModel>
                    println("Button Filter 7 Days Change value")
                    val adapter = HistoryAdapter(historyListFilter)
                    recyclerview.adapter = adapter
                    adapter.setOnItemClickListener(object : HistoryAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            println("Data di klik $position")
                            intenDetailHistory(
                                historyListFilter[position]
                            )
                        }

                    })
                }
            }else{
                indexFilter = -1
                filter7Days.setCardBackgroundColor(Color.WHITE)
                textTitleFilter7Days.setTextColor(Color.BLACK)
                textDescFilter7Days.setTextColor(Color.BLACK)


                println("Button Filter 7 Days Change Color2")
                if (historyListAll.isNotEmpty()){
                    val adapter = HistoryAdapter(historyListAll)
                    recyclerview.adapter = adapter
                    adapter.setOnItemClickListener(object : HistoryAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            println("Data di klik $position")
                            intenDetailHistory(
                                historyListAll[position]
                            )
                        }

                    })
                }
            }


        }

        filter30Days.setOnClickListener(){
            println("Button Filter 30 Days Clicked $indexFilter")
            if (indexFilter != 1){
                indexFilter = 1
                filter30Days.setCardBackgroundColor(getResources().getColor(R.color.colorButton))
                textTitleFilter30Days.setTextColor(Color.WHITE)
                textDescFilter30Days.setTextColor(Color.WHITE)

                filter7Days.setCardBackgroundColor(Color.WHITE)
                textTitleFilter7Days.setTextColor(Color.BLACK)
                textDescFilter7Days.setTextColor(Color.BLACK)

                filter3Months.setCardBackgroundColor(Color.WHITE)
                textTitleFilter3Months.setTextColor(Color.BLACK)
                textDescFilter3Months.setTextColor(Color.BLACK)


                println("Button Filter 30 Days Change Color $indexFilter")
                if (historyListAll.isNotEmpty()){
                    historyListFilter = historyListAll.filter { dataListHistory ->

                        val str = dataListHistory.getDateTime()

                        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                        val currentDate = sdf.format(java.util.Date())

                        val pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

                        val date = LocalDate.parse(currentDate,pattern)
                        val dateMinus30Days = date.minusDays(30)
                        val dataDate = LocalDate.parse(str,pattern)

                        dataDate.isBefore(date).and(dataDate.isAfter(dateMinus30Days))

                    } as MutableList<DetailHistoryTransactionModel>
                    println("Button Filter 30 Days Change value")
                    val adapter = HistoryAdapter(historyListFilter)
                    recyclerview.adapter = adapter
                    adapter.setOnItemClickListener(object : HistoryAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            println("Data di klik $position")
                            intenDetailHistory(
                                historyListFilter[position]
                            )
                        }

                    })
                }
            }else{
                indexFilter = -1
//                filter7Days.setBackgroundColor(Color.WHITE)
                filter30Days.setCardBackgroundColor(Color.WHITE)
                textTitleFilter30Days.setTextColor(Color.BLACK)
                textDescFilter30Days.setTextColor(Color.BLACK)


                println("Button Filter 30 Days Change Color2")
                if (historyListAll.isNotEmpty()){
                    val adapter = HistoryAdapter(historyListAll)
                    recyclerview.adapter = adapter
                    adapter.setOnItemClickListener(object : HistoryAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            println("Data di klik $position")
                            intenDetailHistory(
                                historyListAll[position]
                            )
                        }

                    })
                }
            }


        }

        filter3Months.setOnClickListener(){
            println("Button Filter 3 Months Clicked $indexFilter")
            if (indexFilter != 2){
                indexFilter = 2
                filter3Months.setCardBackgroundColor(getResources().getColor(R.color.colorButton))
                textTitleFilter3Months.setTextColor(Color.WHITE)
                textDescFilter3Months.setTextColor(Color.WHITE)

                filter7Days.setCardBackgroundColor(Color.WHITE)
                textTitleFilter7Days.setTextColor(Color.BLACK)
                textDescFilter7Days.setTextColor(Color.BLACK)

                filter30Days.setCardBackgroundColor(Color.WHITE)
                textTitleFilter30Days.setTextColor(Color.BLACK)
                textDescFilter30Days.setTextColor(Color.BLACK)


                println("Button Filter 3 Months Change Color $indexFilter")
                if (historyListAll.isNotEmpty()){
                    historyListFilter = historyListAll.filter { dataListHistory ->

                        val str = dataListHistory.getDateTime()

                        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                        val currentDate = sdf.format(java.util.Date())

                        val pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

                        val date = LocalDate.parse(currentDate,pattern)
                        val dateMinus3Months = date.minusMonths(3)
                        println("dateMinus3Months " + dateMinus3Months)

                        val dataDate = LocalDate.parse(str,pattern)

                        dataDate.isBefore(date).and(dataDate.isAfter(dateMinus3Months))

                    } as MutableList<DetailHistoryTransactionModel>
                    println("Button Filter 3 Months Change value")
                    val adapter = HistoryAdapter(historyListFilter)
                    recyclerview.adapter = adapter
                    adapter.setOnItemClickListener(object : HistoryAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            println("Data di klik $position")
                            intenDetailHistory(
                                historyListFilter[position]
                            )
                        }

                    })
                }
            }else{
                indexFilter = -1
                filter3Months.setCardBackgroundColor(Color.WHITE)
                textTitleFilter3Months.setTextColor(Color.BLACK)
                textDescFilter3Months.setTextColor(Color.BLACK)


                println("Button Filter 3 Months Change Color2")
                if (historyListAll.isNotEmpty()){
                    val adapter = HistoryAdapter(historyListAll)
                    recyclerview.adapter = adapter
                    adapter.setOnItemClickListener(object : HistoryAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            println("Data di klik $position")
                            intenDetailHistory(
                                historyListAll[position]
                            )
                        }

                    })
                }
            }


        }
    }

    fun intenDetailHistory(data: DetailHistoryTransactionModel){
        val gson = Gson()
        val i = Intent(this, DetailHistoryActivity::class.java)
        println("DataDetailHistory " + gson.toJson(data))
        i.putExtra("dataDetailHistory", gson.toJson(data))
        startActivity(i)
    }


}