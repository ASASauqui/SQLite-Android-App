package com.example.sqlite_1.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_1.DeleteProduct
import com.example.sqlite_1.Product
import com.example.sqlite_1.R
import com.example.sqlite_1.UpdateProduct


class ProductListAdapter: RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    var productList: ArrayList<Product> = arrayListOf()

    constructor(productList: ArrayList<Product>){
        this.productList = productList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item_list, null, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.viewName.text = this.productList[position].name
        holder.viewPrice.text = "$" + this.productList[position].price.toString()
        holder.viewQty.text = this.productList[position].qty.toString() + (if (this.productList[position].qty != 1) " unidades" else " unidad")
    }

    override fun getItemCount(): Int {
        return this.productList.size
    }


    inner class ProductViewHolder: RecyclerView.ViewHolder {
        lateinit var viewName: TextView
        var viewPrice: TextView
        var viewQty: TextView

        constructor(itemView: View) : super(itemView) {
            this.viewName = itemView.findViewById<TextView>(R.id.viewName)
            this.viewPrice = itemView.findViewById<TextView>(R.id.viewPrice)
            this.viewQty = itemView.findViewById<TextView>(R.id.viewQty)
            val btnEdit = itemView.findViewById<Button>(R.id.btnEdit)
            val btnDelete = itemView.findViewById<Button>(R.id.btnDelete)

            btnEdit.setOnClickListener { view ->
                Intent(view.context, UpdateProduct::class.java).also {
                    val context = view.context
                    it.putExtra("ID", productList[adapterPosition].id)
                    context.startActivity(it)
                }
            }

            btnDelete.setOnClickListener { view ->
                Intent(view.context, DeleteProduct::class.java).also {
                    val context = view.context
                    it.putExtra("ID", productList[adapterPosition].id)
                    context.startActivity(it)
                }
            }
        }
    }
}






