package com.hucet.tdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.hucet.tdd.adapter.DataBoundListAdapter
import com.hucet.tdd.databinding.ItemSampleBinding
import java.util.concurrent.Executors

class SampleAdapter constructor(
    private val callback: ((Sample) -> Unit)?
) : DataBoundListAdapter<Sample, ItemSampleBinding>(
    executors = Executors.newSingleThreadExecutor(),
    diffCallback = object : DiffUtil.ItemCallback<Sample>() {
        override fun areItemsTheSame(oldItem: Sample, newItem: Sample): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Sample, newItem: Sample): Boolean {
            return oldItem.title == newItem.title
        }
    }) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ItemSampleBinding {
        val binding = DataBindingUtil.inflate<ItemSampleBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_sample,
            parent,
            false
        )
        return binding
    }

    override fun bind(binding: ItemSampleBinding, item: Sample, position: Int) {
        binding.sample = item
    }
}