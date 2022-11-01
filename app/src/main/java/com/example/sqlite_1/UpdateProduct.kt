package com.example.sqlite_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UpdateProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)

        val context = this

        var id = 0
        val txtName = findViewById<EditText>(R.id.txtName)
        val txtPrice = findViewById<EditText>(R.id.txtPrice)
        val txtQty = findViewById<EditText>(R.id.txtQty)
        val btnSave = findViewById<Button>(R.id.btnSave)
//        val btnHome = findViewById<Button>(R.id.btnHome)
        var isValid: Boolean = false

        if(savedInstanceState == null){
            var extras = intent.extras

            if(extras == null){
                id = Integer.parseInt(null)
            }
            else{
                id = extras.getInt("ID")
            }
        }
        else{
            id = savedInstanceState.getSerializable("ID") as Int
        }

        val db = DatabaseHandler(context)
        val product = db.readDataProduct(id)

        if(product != null){
            txtName.setText(product.name)
            txtPrice.setText(product.price.toString())
            txtQty.setText(product.qty.toString())
        }

        btnSave.setOnClickListener { view ->
            val nameText = txtName.text.toString()
            val priceText = txtPrice.text.toString()
            val qtyText = txtQty.text.toString()

            if(nameText.isNotEmpty() && priceText.isNotEmpty() && qtyText.isNotEmpty()){
                isValid = db.updateData(id, nameText, priceText.toFloat(), qtyText.toInt())

                txtName.setText(nameText)
                txtPrice.setText(priceText)
                txtQty.setText(qtyText)

                if(isValid){
                    Toast.makeText(context, "PRODUCTO ACTUALIZADO", Toast.LENGTH_SHORT).show()

                    Intent(view.context, MainActivity::class.java).also {
                        context.startActivity(it)
                    }
                }
                else{
                    Toast.makeText(context, "ERROR AL ACTUALIZAR", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(context, "ALGUNOS CAMPOS ESTÁN VACÍOS. RELLÉNELOS PARA ACTUALIZAR", Toast.LENGTH_SHORT).show()
            }
        }

//        btnHome.setOnClickListener {
//            Intent(this, MainActivity::class.java).also {
//                startActivity(it)
//            }
//        }
    }
}