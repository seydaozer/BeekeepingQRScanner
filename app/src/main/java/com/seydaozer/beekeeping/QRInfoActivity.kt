package com.seydaozer.beekeeping

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class QRInfoActivity : AppCompatActivity() {

    private lateinit var qrInfoTextView: TextView
    private lateinit var hiveNumberTextView: TextView
    private lateinit var noQueenBeeButton: Button
    private lateinit var queenBeeButton: Button
    private lateinit var missingQueenButton: Button
    private lateinit var activeDayButton: Button
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_info)

        qrInfoTextView = findViewById(R.id.qrInfoTextView)
        hiveNumberTextView = findViewById(R.id.hiveNumberTextView)
        noQueenBeeButton = findViewById(R.id.noQueenBeeButton)
        queenBeeButton = findViewById(R.id.queenBeeButton)
        missingQueenButton = findViewById(R.id.missingQueenButton)
        activeDayButton = findViewById(R.id.activeDayButton)
        saveButton = findViewById(R.id.saveButton)

        // QR kodundan gelen bilgiyi al ve qrInfoTextView'e yerleştir
        val qrCodeInfo = "Hive Information"
        qrInfoTextView.text = qrCodeInfo

        val hiveNumber = intent.getStringExtra("QR_CODE_DATA")
        hiveNumberTextView.text = hiveNumber

        // Butonlara tıklama işlemleri ekle
        noQueenBeeButton.setOnClickListener {
            selectButton("No Queen Bee")
        }

        queenBeeButton.setOnClickListener {
            selectButton("Queen Bee")
        }

        missingQueenButton.setOnClickListener {
            selectButton("Missing Queen")
        }

        activeDayButton.setOnClickListener {
            selectButton("Active Day")
        }

        saveButton.setOnClickListener {
            saveData()
        }
    }

    private fun selectButton(buttonText: String) {
        // Seçilen butona çerçeve ekle
        noQueenBeeButton.isSelected = buttonText == "No Queen Bee"
        queenBeeButton.isSelected = buttonText == "Queen Bee"
        missingQueenButton.isSelected = buttonText == "Missing Queen"
        activeDayButton.isSelected = buttonText == "Active Day"
    }

    private fun saveData() {
        // Veriyi kaydetmek için gerekli işlemleri gerçekleştir
        val qrCodeInfo = qrInfoTextView.text.toString()
        val timeStamp = System.currentTimeMillis().toString()
        val selectedButton = when {
            noQueenBeeButton.isSelected -> "No Queen Bee"
            queenBeeButton.isSelected -> "Queen Bee"
            missingQueenButton.isSelected -> "Missing Queen"
            activeDayButton.isSelected -> "Active Day"
            else -> ""
        }
        val dataToSave = "$qrCodeInfo-$timeStamp-$selectedButton"
        // Veriyi kaydetme işlemi burada yapılabilir

        // Kaydedildi mesajını göster ve MainActivity'ye geçiş yap
        showToast("Kaydedildi")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // QRInfoActivity'yi kapat
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
