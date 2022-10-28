package com.example.sqlite_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_1.adapters.ProductListAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this

        var db = DatabaseHandler(context)

        var btnInsert = findViewById<Button>(R.id.btnInsert)


        btnInsert.setOnClickListener {
            Intent(this, NewProduct::class.java).also {
                startActivity(it)
            }
        }

        var productList = findViewById<RecyclerView>(R.id.productList)
        var adapter = ProductListAdapter(db.readData())
        productList.adapter = adapter

    }
}