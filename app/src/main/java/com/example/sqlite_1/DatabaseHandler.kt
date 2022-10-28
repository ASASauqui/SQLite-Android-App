package com.example.sqlite_1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "ProductsDB1"
val TABLE_NAME = "Products"
val COL_NAME = "name"
val COL_PRICE = "price"
val COL_QTY = "qty"
val COL_ID = "id"

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

        var result = db.insert(TABLE_NAME, null, cv)

        if(result == -1.toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readData() : ArrayList<Product> {
        var list : ArrayList<Product> = ArrayList()

        var db = this.readableDatabase

        val query = "Select * from " + TABLE_NAME;

        val result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do {
                var product = Product()
                product.id = result.getString(0).toInt()
                product.name = result.getString(1)
                product.price = result.getString(2).toFloat()

                list.add(product)
            } while(result.moveToNext());
        }

        result.close()
        db.close()
        return list
    }
}