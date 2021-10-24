package com.example.ecom.package

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter : RecyclerView.Adapter<> {

    class ItemViewHolder
    constructor(itemView:View): RecyclerView.ViewHolder(itemView){

        val name = itemView.name



    }

}