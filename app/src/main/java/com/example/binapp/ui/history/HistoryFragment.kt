package com.example.binapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.binapp.R
import com.example.binapp.databinding.FragmentHistoryBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = requireNotNull(_binding) { "Binding is null" }

    private val viewModel by viewModel<HistoryViewModel>()

    private val historyAdapter = HistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyAdapter.onLongClickListener = HistoryViewHolder.OnLongClickListener { binInfo ->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(requireActivity().getString(R.string.dialog_title))
                .setNegativeButton(
                    requireActivity().getString(R.string.dialog_negative)
                ) { _, _ ->
                }
                .setPositiveButton(
                    requireActivity().getString(R.string.dialog_positive)
                ) { _, _ ->
                    viewModel.deleteBinInfoFromHistory(binInfo)
                }
                .show()
        }

        binding.rv.adapter = historyAdapter

        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            renderState(state)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            historyAdapter.submitList(it)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderState(state: HistoryScreenState) {
        binding.rv.isVisible = state is HistoryScreenState.Content
        binding.tvEmpty.isVisible = state is HistoryScreenState.Empty
    }

    companion object {
        fun newInstance() =
            HistoryFragment()
    }
}