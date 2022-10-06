package com.finpay.wallet.view.qris

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.finpay.wallet.databinding.FragmentQrisBinding
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.integration.android.IntentIntegrator
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class QRISFragment : Fragment() {

    private var WRITE_EXTERNAL_STORAGE_PERMISSION_CODE: Int = 1
    private var READ_EXTERNAL_STORAGE_PERMISSION_CODE: Int = 2
    private var CAMERA_PERMISSION_CODE: Int = 3

    private var _binding: FragmentQrisBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val qrisViewModel =
            ViewModelProvider(this).get(QRISViewModel::class.java)

        _binding = FragmentQrisBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        qrisViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

//        val intent = Intent(getContext(), QRISActivity::class.java)
//        startActivity(intent)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun checkPermission(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            when (PackageManager.PERMISSION_DENIED) {
//                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
//                    requestPermissions(
//                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                        WRITE_EXTERNAL_STORAGE_PERMISSION_CODE
//                    )
//                }
//                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) -> {
//                    requestPermissions(
//                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//                        READ_EXTERNAL_STORAGE_PERMISSION_CODE
//                    )
//                }
//                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) -> {
//                    requestPermissions(
//                        arrayOf(Manifest.permission.CAMERA),
//                        CAMERA_PERMISSION_CODE
//                    )
//                }
//            }
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            WRITE_EXTERNAL_STORAGE_PERMISSION_CODE -> if (grantResults.isNotEmpty()) {
//                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
//                    Toast.makeText(getContext(), "Anda perlu memberikan semua izin untuk menggunakan aplikasi ini.", Toast.LENGTH_SHORT).show()
////                    finish()
//                }
//            }
//            READ_EXTERNAL_STORAGE_PERMISSION_CODE -> if (grantResults.isNotEmpty()) {
//                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
//                    Toast.makeText(getContext(), "Anda perlu memberikan semua izin untuk menggunakan aplikasi ini.", Toast.LENGTH_SHORT).show()
////                    finish()
//                }
//            }
//            CAMERA_PERMISSION_CODE -> if (grantResults.isNotEmpty()) {
//                if (grantResults[0] == PackageManager.PERMISSION_DENIED){
//                    Toast.makeText(getContext(), "Anda perlu memberikan semua izin untuk menggunakan aplikasi ini.", Toast.LENGTH_SHORT).show()
////                    finish()
//                }
//            }
//        }
//    }
//
//    private fun openCamera() {
//        val qrScan = IntentIntegrator(requireContext())
//        qrScan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
//        qrScan.setPrompt("Scan a QR Code")
//        qrScan.setOrientationLocked(false)
//        qrScan.setBeepEnabled(true)
//        qrScan.setBarcodeImageEnabled(true)
//        //initiating the qr code scan
//        qrScan.initiateScan()
//    }
//
//    private fun openGallery() {
//        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        resultLauncherGallery.launch(galleryIntent)
//    }
//
//    var resultLauncherGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            val data: Intent? = result.data
//
//            val imageUri = data!!.data!!
//            val imagePath = convertMediaUriToPath(imageUri)
//            val imgFile = File(imagePath)
//            scanImageQRCode(imgFile)
//        } else {
//            Toast.makeText(getContext(), "Result Not Found", Toast.LENGTH_LONG).show()
//        }
//    }
//
//    private fun convertMediaUriToPath(uri: Uri):String {
//        val proj = arrayOf<String>(MediaStore.Images.Media.DATA)
//        val cursor = contentResolver.query(uri, proj, null, null, null)
//        val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//        cursor.moveToFirst()
//        val path = cursor.getString(columnIndex)
//        cursor.close()
//        return path
//    }
//
//    private fun scanImageQRCode(file: File){
//        val inputStream: InputStream = BufferedInputStream(FileInputStream(file))
//        val bitmap = BitmapFactory.decodeStream(inputStream)
//        val decoded = scanQRImage(bitmap)
//        Log.i("QrTest", "Decoded string=$decoded")
//    }
//
//    private fun scanQRImage(bMap: Bitmap): String? {
//        var contents: String? = null
//        val intArray = IntArray(bMap.width * bMap.height)
//        //copy pixel data from the Bitmap into the 'intArray' array
//        bMap.getPixels(intArray, 0, bMap.width, 0, 0, bMap.width, bMap.height)
//        val source: LuminanceSource = RGBLuminanceSource(bMap.width, bMap.height, intArray)
//        val bitmap = BinaryBitmap(HybridBinarizer(source))
//        val reader: Reader = MultiFormatReader()
//        try {
//            val result: Result = reader.decode(bitmap)
//            contents = result.text
//            println("result")
//            println(contents)
//            //tvResult.text = contents
//        } catch (e: Exception) {
//            Log.e("QrTest", "Error decoding qr code", e)
//            Toast.makeText(getContext(), "Error decoding QR Code, Mohon pilih gambar QR Code yang benar!", Toast.LENGTH_SHORT).show()
//        }
//        return contents
//    }
}