package com.example.sqlite_1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_1.Product
import com.example.sqlite_1.R

class ProductListAdapter: RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    var productList: ArrayList<Product> = arrayListOf()

    constructor(productList: ArrayList<Product>){
        this.productList = productList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.product_item_list, null, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.viewName.setText(productList.get(position).name.toString())
        holder.viewPrice.setText(productList.get(position).price.toString())
        holder.viewQty.setText(productList.get(position).qty.toString())
    }

    override fun getItemCount(): Int {
        return productList.size
    }


    class ProductViewHolder: RecyclerView.ViewHolder {

        lateinit var viewName: TextView
        lateinit var viewPrice: TextView
        lateinit var viewQty: TextView

        constructor(itemView: View) : super(itemView) {
            this.viewName = itemView.findViewById<TextView>(R.id.viewName)
            this.viewPrice = itemView.findViewById<TextView>(R.id.viewPrice)
            this.viewQty = itemView.findViewById<TextView>(R.id.viewQty)
        }
    }

}