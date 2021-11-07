package com.example.ecom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

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
@Entity(tableName = "item_table")
data class Item(val name:String,
                @PrimaryKey(autoGenerate = false) val id:Int,
                val synonyms:String,
                val brand:String,
                val price:Int,
                val sizeInt:Int,
                val size:String,
                val unit:String,
                val imageUrl:String
                )