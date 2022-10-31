package com.example.sqlite_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_1.adapters.ProductListAdapter

class ViewProductList : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_view_product_list, container, false)

        val db = DatabaseHandler(root.context)

        val viewProductList = root.findViewById<RecyclerView>(R.id.productList)
        viewProductList.layoutManager = LinearLayoutManager(root.context)

        val adapter = ProductListAdapter(db.readData())
        viewProductList.adapter = adapter

        return root
    }
}