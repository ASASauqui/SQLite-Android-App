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
        println("here onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item_list, null, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        println("here onBindViewHolder")
        holder.viewName.text = productList[position].name
        holder.viewPrice.text = productList[position].price.toString()
        holder.viewQty.text = productList[position].qty.toString()
    }

    override fun getItemCount(): Int {
        println("here getItemCount")
        return productList.size
    }


    class ProductViewHolder: RecyclerView.ViewHolder {

        lateinit var viewName: TextView
        lateinit var viewPrice: TextView
        lateinit var viewQty: TextView

        constructor(itemView: View) : super(itemView) {
            println("here ProductViewHolder constructor")
            this.viewName = itemView.findViewById<TextView>(R.id.viewName)
            this.viewPrice = itemView.findViewById<TextView>(R.id.viewPrice)
            this.viewQty = itemView.findViewById<TextView>(R.id.viewQty)
        }
    }

}