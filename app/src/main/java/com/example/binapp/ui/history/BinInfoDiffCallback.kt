package com.example.binapp.ui.history

import androidx.recyclerview.widget.DiffUtil
import com.example.binapp.domain.model.BinInfo

class BinInfoDiffCallback : DiffUtil.ItemCallback<BinInfo>() {
    override fun areItemsTheSame(oldItem: BinInfo, newItem: BinInfo): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(oldItem: BinInfo, newItem: BinInfo): Boolean {
        return oldItem == newItem
    }
}