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


        var db = DatabaseHandler(context)

        var txtName = findViewById<EditText>(R.id.txtName)
        var txtPrice = findViewById<EditText>(R.id.txtPrice)
        var txtQty = findViewById<EditText>(R.id.txtQty)
        var btnSave = findViewById<Button>(R.id.btnSave)
        var btnHome = findViewById<Button>(R.id.btnHome)


        btnSave.setOnClickListener {
            if(txtName.text.toString().length > 0 &&
                txtPrice.text.toString().length> 0 &&
                txtQty.text.toString().length> 0) {
                var product = Product(txtName.text.toString(), txtPrice.text.toString().toFloat(), txtQty.text.toString().toInt())

                db.insertData(product)
            }
            else{
                Toast.makeText(context, "PLease Fill all data", Toast.LENGTH_SHORT).show()
            }

        }

        btnHome.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }




    }
}