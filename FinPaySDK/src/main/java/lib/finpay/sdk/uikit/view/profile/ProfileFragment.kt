package lib.finpay.sdk.uikit.view.profile

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import lib.finpay.sdk.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.databinding.FragmentProfileBinding
import lib.finpay.sdk.uikit.view.profile.edit_profile.EditProfileActivity
import lib.finpay.sdk.uikit.view.profile.pin.ChangePinActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var txtUserName: TextView
    private lateinit var txtPhoneNumber: TextView
    private lateinit var txtStatus: TextView

    //    private lateinit var imgProfile: ImageView
    private lateinit var finPaySDK: FinpaySDK
    private lateinit var changeProfile: LinearLayout
    private lateinit var buttonUbahPin: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        finPaySDK = FinpaySDK()
        val notificationsViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        txtUserName = binding.txtUsername
        txtPhoneNumber = binding.txtPhoneNumber
        txtStatus = binding.txtStatus
        changeProfile = binding.changeProfile
        buttonUbahPin = binding.buttonUbahPin
//        imgProfile = binding.imgProfile
        //getBalance()

        changeProfile.setOnClickListener {
            showDialogProfile()
        }

        buttonUbahPin.setOnClickListener {
            val intent = Intent(context, ChangePinActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun getBalance() {
//        val userName = "MT77764DKM83N"
//        val password = "YJV3AM0y"
//        val secretKey = "daYumnMb"
//        finPaySDK.getBalance(
//            userName,
//            password,
//            secretKey,
//            "TRX1234567890",
//            "083815613839",
//            onSuccess = { userBalanceModel ->
//                txtPhoneNumber.text = "6283815613839"
//                txtUserName.text = userBalanceModel.getCustName()
//                var custType: String = "STANDAR"
//                if(userBalanceModel.getCustType().toString() == "UNREGISTER") {
//                    custType = "STANDAR"
//                }else{
//                    "PREMIUM"
//                }
//                txtStatus.text = custType
//            }
//        )
//    }

    fun showDialogProfile() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.bottom_dialog_profile)
        val btnChangePhoto = dialog.findViewById<RelativeLayout>(R.id.rlChangePhoto)
        val btnQR = dialog.findViewById<RelativeLayout>(R.id.rlQR)

        btnChangePhoto?.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }
        btnQR?.setOnClickListener {
            dialog.dismiss()
            openQrDialog()
        }
        dialog.show()
    }

    private fun openQrDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_share_qr)

        val btnShare = dialog.findViewById<Button>(R.id.btn_share_qr)
        btnShare.setOnClickListener {
            val qrImage = dialog.findViewById<ImageView>(R.id.qr_image)
            val mDrawable: Drawable = qrImage.drawable
            val mBitmap = (mDrawable as BitmapDrawable).bitmap
            val path = MediaStore.Images.Media.insertImage(
                activity!!.contentResolver,
                mBitmap,
                "Image Description",
                null
            )
            val uri = Uri.parse(path)
            val share = Intent(Intent.ACTION_SEND)
            share.type = "image/*"
            share.putExtra(Intent.EXTRA_STREAM, uri)
            startActivity(Intent.createChooser(share, "Bagikan QR kode"))
//            dialog.dismiss()
        }
        dialog.show()
    }
}