package com.example.binapp.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.binapp.databinding.ViewBinInfoItemBinding
import com.example.binapp.domain.model.BinInfo

class HistoryAdapter : ListAdapter<BinInfo, HistoryViewHolder>(BinInfoDiffCallback()) {

    var onLongClickListener: HistoryViewHolder.OnLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewBinInfoItemBinding.inflate(layoutInflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val binInfo = getItem(position)
        holder.bind(binInfo, onLongClickListener)
    }
}