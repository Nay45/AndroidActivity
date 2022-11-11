package com.example.ea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //private artinya variabel-variabel yang didefinisikan bisaa diakses dalam satu class.
    //lateinit digunakan untuk menginisialisasi objek atau properti-properti yang digunakan.
    //var artinya Object Mutable yang dapat di ubah isinya atau di ganti nilainya.
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView
    //onCreate() adalah kondisi awal saat Activity baru diciptakan, biasanya dilakukan inisialisasi pada tahapan ini.
    //savedInstanceState adalah metode untuk menerima parameter yang merupakan objek Bundle yang berisi status aktivitas yang sebelumnya disimpan.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //findViewById(R.id....) digunakan untuk mengenali id TextView yang sudah kita buat.

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        //setOnClickListener menjalankan suatu instruksi yang sudah ditentukan sebelumnya

        btnCalculate.setOnClickListener(this)

        //pernyataan yang berfungsi jika savedInstanceState tidak kosong maka hasil akan ditampilkan.

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    //onClick() berfungsi untuk menghandle saat tombol diklik.
    override fun onClick(v: View) {
        if  (v.id == R.id.btn_calculate) {
            //val artinya Object Immutable yang tidak dapat di ubah isinya atau di ganti nilainya.
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

            //isEmptyFields adalah metode yang digunakan untuk mengecek isi kolom EditText.
            var isEmptyFields = false

            //jika input kosong maka akan muncul pernyataan error "Field Ini Tidak Boleh Kosong".
            if (inputLength.isEmpty()) {
                isEmptyFields = true
                edtLength.error = "Field Ini Tidak Boleh Kosong"
            }

            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                edtWidth.error = "Field Ini Tidak Boleh Kosong"
            }

            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                edtHeight.error = "Field Ini Tidak Boleh Kosong"
            }

            //jika input tidak kosong maka hasil akan ditampilkan.
            if (!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                tvResult.text = volume.toString()
            }
        }
    }

    //companion object berfungsi untuk memberi variabel di suatu kelas agar bisa dipanggil tanpa melalui sebuah objek.
    companion object {
        private const val STATE_RESULT = "state_result"
    }

    //onSaveInstanceState digunakan untuk situasi dimana Activity dapat dibuat ulang dengan menyimpan dan mengembalikan state Actibvity sebelumnya, misalnya saat screen rotation (perubahan orientasi layar).
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
}