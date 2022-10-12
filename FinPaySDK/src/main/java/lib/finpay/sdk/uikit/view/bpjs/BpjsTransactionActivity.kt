package lib.finpay.sdk.uikit.view.bpjs

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import lib.finpay.sdk.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class BpjsTransactionActivity : AppCompatActivity() {
    lateinit var btnChoosePeriodeTime: LinearLayout
    lateinit var periodeTime:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bpjs_transaction)
        supportActionBar!!.hide()

        btnChoosePeriodeTime = findViewById(R.id.choosePeriodeTime)
        periodeTime = findViewById(R.id.selectedPeriodeMonth)

        btnChoosePeriodeTime.setOnClickListener{
            val dialog = BottomSheetDialog(this)
            val monthArray = arrayOf(
                "Oktober 2022", "November 2022", "Desember 2022", "Januari 2023",
                "Februari 2023", "Maret 2023", "April 2023", "Mei 2023","Juni 2023","Juli 2023",
                "Agustus 2023","September 2023"
            )

            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog_bpjs, null)
//            val adapter: ArrayAdapter<*> = ArrayAdapter(
//                this,
//                R.layout.activity_listview, monthArray
//            )
            dialog.setCancelable(true)
            dialog.setContentView(view)
            dialog.show()
        }
    }
}