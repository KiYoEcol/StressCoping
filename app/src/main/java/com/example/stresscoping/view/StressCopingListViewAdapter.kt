package com.example.stresscoping

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stresscoping.databinding.ItemStressCopingListBinding
import com.example.stresscoping.model.StressCopingModel
import com.example.stresscoping.viewmodel.StressCopingListViewModel

private object DiffCallback : DiffUtil.ItemCallback<StressCopingModel>() {
    override fun areItemsTheSame(oldItem: StressCopingModel, newItem: StressCopingModel): Boolean {
        return oldItem.stressCoping == newItem.stressCoping
    }

    override fun areContentsTheSame(
        oldItem: StressCopingModel,
        newItem: StressCopingModel
    ): Boolean {
        return oldItem == newItem
    }

}

class StressCopingListViewAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: StressCopingListViewModel,
) : ListAdapter<StressCopingModel, StressCopingListViewAdapter.StressCopingListViewHolder>(
    DiffCallback
) {
    class StressCopingListViewHolder(val binding: ItemStressCopingListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            model: StressCopingModel,
            lifecycleOwner: LifecycleOwner,
            viewModel: StressCopingListViewModel
        ) {
            binding.run {
                this.lifecycleOwner = lifecycleOwner
                this.stressCoping = model
                this.root.setOnClickListener {
                    viewModel.onClickItem(model)
                }

                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StressCopingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStressCopingListBinding.inflate(inflater, parent, false)
        return StressCopingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StressCopingListViewHolder, position: Int) {
        holder.bind(getItem(position), lifecycleOwner, viewModel)
    }
}