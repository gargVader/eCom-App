package com.example.ecom

/**
 * {
    "name": "Alphonso Mango",
    "id": 1,
    "synonyms": "Alphonso Mangoes",
    "brand": "Fresho",
    "price": 155,
    "sizeInt": 1,
    "size": "1kg",
    "unit": "kg",
    "imageUrl": ""
  }
 */
data class Item(val name:String,
                val id:Int,
                val synonyms:String,
                val brand:String,
                val price:Int,
                val sizeInt:Int,
                val size:String,
                val unit:String,
                val imageUrl:String
                )