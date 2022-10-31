package com.example.sqlite_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_1.adapters.ProductListAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ViewProductList()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

        val btnInsert = findViewById<Button>(R.id.btnInsert)

        btnInsert.setOnClickListener {
            Intent(this, NewProduct::class.java).also {
                startActivity(it)
            }
        }
    }
}