package com.example.sqlite_1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception

const val DATABASE_NAME = "ProductsDB1"
const val TABLE_NAME = "Products"
const val COL_NAME = "name"
const val COL_PRICE = "price"
const val COL_QTY = "qty"
const val COL_ID = "id"

class DatabaseHandler(var context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(255)," +
                COL_PRICE + " FLOAT,"+
                COL_QTY + " INT)"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(product: Product){
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COL_NAME, product.name)
        cv.put(COL_PRICE, product.price)
        cv.put(COL_QTY, product.qty)

        val result = db.insert(TABLE_NAME, null, cv)

        if (result == (-1).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateData(id: Int, name: String, price: Float, qty: Int) : Boolean {
        val db = this.writableDatabase

        var isValid = false

        val updateProduct = "UPDATE $TABLE_NAME " +
                "SET $COL_NAME= '$name', $COL_PRICE = '$price', $COL_QTY = '$qty' " +
                "WHERE $COL_ID = '$id'"

        try{
            db.execSQL(updateProduct)
            isValid = true
        }
        catch (ex: Exception){
            ex.toString()
            isValid = false
        }
        finally {
            db.close()
        }

        return isValid
    }

    fun readData() : ArrayList<Product> {
        val list : ArrayList<Product> = ArrayList()

        val db = this.readableDatabase

        val query = "Select * from $TABLE_NAME"

        val result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do {
                val product = Product()
                product.id = result.getString(0).toInt()
                product.name = result.getString(1)
                product.price = result.getString(2).toFloat()
                product.qty = result.getString(3).toInt()

                list.add(product)
            } while(result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun readDataProduct(id: Int): Product {
        val db = this.readableDatabase

        val query = "Select * from $TABLE_NAME WHERE id = $id LIMIT 1"

        val result = db.rawQuery(query, null)

        val product = Product();

        if (result.moveToFirst()){
            product.id = result.getString(0).toInt()
            product.name = result.getString(1)
            product.price = result.getString(2).toFloat()
            product.qty = result.getString(3).toInt()
        }

        result.close()
        db.close()

        return product;
    }
}