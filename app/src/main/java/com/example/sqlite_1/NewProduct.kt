package com.example.sqlite_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class NewProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_product)
        val context = this

        val db = DatabaseHandler(context)

        val txtName = findViewById<EditText>(R.id.txtName)
        val txtPrice = findViewById<EditText>(R.id.txtPrice)
        val txtQty = findViewById<EditText>(R.id.txtQty)
        val btnSave = findViewById<Button>(R.id.btnSave)
//        val btnHome = findViewById<Button>(R.id.btnHome)

        btnSave.setOnClickListener {
            if (txtName.text.toString().isNotEmpty() &&
                txtPrice.text.toString().isNotEmpty() &&
                txtQty.text.toString().isNotEmpty()
            ) {
                val product = Product(txtName.text.toString(), txtPrice.text.toString().toFloat(), txtQty.text.toString().toInt())

                db.insertData(product)

                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                }
            } else {
                Toast.makeText(context, "POR FAVOR RELLENE TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
            }

        }

//        btnHome.setOnClickListener {
//            Intent(this, MainActivity::class.java).also {
//                startActivity(it)
//            }
//        }
    }
}
