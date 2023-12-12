package com.example.stresscoping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stresscoping.databinding.ItemStressCopingListBinding

class StressCopingListViewAdapter(
    private val list: List<StressCopingModel>,
    private val listener: Listener?
) : RecyclerView.Adapter<StressCopingListViewAdapter.StressCopingListViewHolder>() {
    interface Listener {
        fun onClickItem(tappedView: View, stressCopingModel: StressCopingModel)
    }

    class StressCopingListViewHolder(val binding: ItemStressCopingListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StressCopingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStressCopingListBinding.inflate(inflater, parent, false)
        return StressCopingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StressCopingListViewHolder, position: Int) {
        holder.binding.textviewStressCopingItem.text = list[position].stressCoping
        holder.binding.root.setOnClickListener { listener?.onClickItem(it, list[position]) }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}