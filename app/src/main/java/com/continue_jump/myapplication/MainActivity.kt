package com.continue_jump.myapplication

import android.os.Bundle
import android.util.AndroidRuntimeException
import android.util.Base64
import com.google.zxing.WriterException
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import android.widget.ImageView
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.EncodeHintType
import com.google.zxing.client.android.Intents.Scan.CHARACTER_SET
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.net.URLEncoder




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)

        button.setOnClickListener {

            //QRコード化する文字列
            val data = editText.text.toString()
            //val data = URLEncoder.encode(s, "utf-8")
            //val data = Base64.encodeToString(s.toByteArray(), Base64.CRLF)

            //QRコード画像の大きさを指定(pixel)
            val size = 500


            try {

                val hints = mapOf<EncodeHintType, Any>(
                    // マージン指定
                    EncodeHintType.MARGIN to 0,
                    // 誤り訂正レベルを指定
                    EncodeHintType.ERROR_CORRECTION to ErrorCorrectionLevel.M,

                    EncodeHintType.CHARACTER_SET to "Shift-JIS"
                )

                val barcodeEncoder = BarcodeEncoder()
                //QRコードをBitmapで作成
                val bitmap = barcodeEncoder.encodeBitmap(data, BarcodeFormat.QR_CODE, size, size, hints)

                //作成したQRコードを画面上に配置
                val imageViewQrCode = findViewById<View>(R.id.imageView) as ImageView
                imageViewQrCode.setImageBitmap(bitmap)

            } catch (e: WriterException) {
                throw AndroidRuntimeException("Barcode Error.", e)
            }
        }

    }
}