package com.example.sqlite_1

class Product {
    var id : Int = 0
    var name : String = ""
    var price : Float = 0f
    var qty: Int = 0


    constructor(name: String, price: Float, qty: Int){
        this.name = name
        this.price = price
        this.qty = qty
    }

    constructor() {}


}