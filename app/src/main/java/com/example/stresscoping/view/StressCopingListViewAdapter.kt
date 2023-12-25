package com.example.stresscoping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stresscoping.databinding.ItemStressCopingListBinding
import com.example.stresscoping.model.StressCopingModel
import com.example.stresscoping.viewmodel.StressCopingListViewModel

private object DiffCallback : DiffUtil.ItemCallback<StressCopingModel>() {
    override fun areItemsTheSame(oldItem: StressCopingModel, newItem: StressCopingModel): Boolean {
        return oldItem.id == newItem.id
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
) : ListAdapter<StressCopingModel, RecyclerView.ViewHolder>(
    DiffCallback
) {
    class StressCopingListViewHolder(private val binding: ItemStressCopingListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            model: StressCopingModel,
            lifecycleOwner: LifecycleOwner,
            viewModel: StressCopingListViewModel
        ) {
            binding.run {
                this.lifecycleOwner = lifecycleOwner
                this.stressCoping = model
                this.buttonEdit.setOnClickListener {
                    viewModel.onClickEditButton(model)
                }
                this.buttonDelete.setOnClickListener {
                    viewModel.onClickDeleteButton(model)
                }

                executePendingBindings()
            }
        }
    }

    class FooterListViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    private val ITEM_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2

    override fun getItemCount(): Int {
        return if (super.getItemCount() == 0) {
            0
        } else {
            super.getItemCount() + 1
        }
    }

    override fun getItem(position: Int): StressCopingModel? {
        return if (position < itemCount - 1) {
            super.getItem(position)
        } else {
            null
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < itemCount - 1) {
            ITEM_VIEW_TYPE
        } else {
            FOOTER_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_VIEW_TYPE) {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemStressCopingListBinding.inflate(inflater, parent, false)
            StressCopingListViewHolder(binding)
        } else {
            val view = FrameLayout(parent.context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    80.dpToPx(parent.context),
                    80.dpToPx(parent.context)
                )
            }
            FooterListViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StressCopingListViewHolder) {
            val stressCoping: StressCopingModel =
                getItem(position)
                    ?: throw IllegalStateException("getItem cannot be null when ViewHolder is StressCopingListViewHolder")
            holder.bind(stressCoping, lifecycleOwner, viewModel)
        }
    }
}