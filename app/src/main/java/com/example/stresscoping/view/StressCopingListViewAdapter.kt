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
import com.example.stresscoping.model.StressCopingListItemModel
import com.example.stresscoping.viewmodel.StressCopingListViewModel

private object DiffCallback : DiffUtil.ItemCallback<StressCopingListItemModel>() {
    override fun areItemsTheSame(
        oldItem: StressCopingListItemModel,
        newItem: StressCopingListItemModel
    ): Boolean {
        return if (oldItem.type == StressCopingListItemModel.Type.Body && newItem.type == StressCopingListItemModel.Type.Body) {
            oldItem.stressCoping?.id == newItem.stressCoping?.id
        } else oldItem.type == StressCopingListItemModel.Type.Footer && newItem.type == StressCopingListItemModel.Type.Footer
    }

    override fun areContentsTheSame(
        oldItem: StressCopingListItemModel,
        newItem: StressCopingListItemModel
    ): Boolean {
        return oldItem == newItem
    }

}

class StressCopingListViewAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: StressCopingListViewModel,
) : ListAdapter<StressCopingListItemModel, RecyclerView.ViewHolder>(
    DiffCallback
) {
    class StressCopingListViewHolder(private val binding: ItemStressCopingListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            position: Int,
            stressCopingListItemModel: StressCopingListItemModel,
            lifecycleOwner: LifecycleOwner,
            viewModel: StressCopingListViewModel
        ) {
            binding.run {
                this.lifecycleOwner = lifecycleOwner
                this.stressCopingListItem = stressCopingListItemModel
                this.layoutTop.setOnClickListener {
                    viewModel.onClickItem(position, stressCopingListItemModel)
                }
                this.layoutTop.setOnLongClickListener {
                    viewModel.onLongClickItem(stressCopingListItemModel)
                    true
                }
                this.buttonEdit.setOnClickListener {
                    stressCopingListItemModel.stressCoping?.let { viewModel.onClickEditButton(it) }
                }
                this.buttonDelete.setOnClickListener {
                    stressCopingListItemModel.stressCoping?.let { viewModel.onClickDeleteButton(it) }
                }

                executePendingBindings()
            }
        }
    }

    class FooterListViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    private val ITEM_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2

    override fun getItemViewType(position: Int): Int {
        val stressCopingListItem = getItem(position)
        return when (stressCopingListItem.type) {
            StressCopingListItemModel.Type.Body -> ITEM_VIEW_TYPE
            StressCopingListItemModel.Type.Footer -> FOOTER_VIEW_TYPE
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
            val stressCopingListItemModel = getItem(position)
            if (stressCopingListItemModel.stressCoping == null) {
                throw IllegalStateException("StressCopingModel cannot be null when holder is StressCopingListViewHolder")
            }
            holder.bind(position, stressCopingListItemModel, lifecycleOwner, viewModel)
        }
    }
}