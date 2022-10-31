package com.example.sqlite_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class DeleteProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_product)

        val context = this

        var id = 0
        val txtConfirm = findViewById<TextView>(R.id.txtConfirm)
        val btnAccept  =findViewById<Button>(R.id.btnAccept)
        val btnCancel  =findViewById<Button>(R.id.btnCancel)

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
            txtConfirm.setText("¿Desea eliminar el producto " + product.name + "?")
        }


        btnCancel.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }

        btnAccept.setOnClickListener {

            if(db.deleteOne(product.id)){
                Toast.makeText(context, "PRODUCTO ELIMINADO", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "ERROR AL ELIMINAR", Toast.LENGTH_SHORT).show()
            }


            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }



    }
}