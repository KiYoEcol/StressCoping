package com.example.stresscoping

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StressCopingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.textview_stress_coping_item)
}