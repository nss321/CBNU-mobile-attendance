package com.qr_checkin.qr_test

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qr_checkin.qr_test.databinding.ActivityMakeQrcodeBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class MakeQRCode : AppCompatActivity() {

    private val binding by lazy {
        ActivityMakeQrcodeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //인텐트 받아옴
        val qrContents = intent.getStringExtra("qrContents")

        createQRCode(qrContents)

        binding.btnBack.setOnClickListener{
            finish()
        }
    }
    //인텐트에서 받아온 정보를 담아 bitmap type으로 QR생성
    fun createQRCode(qrContents:String?){
        val qrCode = QRCodeWriter()
        val bitMtx = qrCode.encode(
            qrContents,
            BarcodeFormat.QR_CODE,
            350,
            350
        )
        val bitmap: Bitmap = Bitmap.createBitmap(
            bitMtx.width,
            bitMtx.height,
            Bitmap.Config.RGB_565
        )
        for(i in 0 until bitMtx.width){
            for(j in 0 until bitMtx.height){
                var color: Int = 0
                if(bitMtx.get(i,j)){
                    color = Color.BLACK
                }else{
                    color = Color.WHITE
                }
                bitmap.setPixel(i,j,color)
            }
        }
        binding.qrImage.setImageBitmap(bitmap)
    }
}