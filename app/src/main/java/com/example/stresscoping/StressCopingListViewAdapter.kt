package com.example.stresscoping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StressCopingListViewAdapter(
    private val list: List<StressCopingModel>,
    private val listener: Listener?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface Listener {
        fun onClickItem(tappedView: View, stressCopingModel: StressCopingModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stress_coping_list, parent, false)
        return StressCopingListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? StressCopingListViewHolder)?.apply {
            textView.text = list[position].stressCoping
            itemView.setOnClickListener { listener?.onClickItem(it, list[position]) }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}