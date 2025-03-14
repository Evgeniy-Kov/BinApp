package com.example.binapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.binapp.R
import com.example.binapp.databinding.FragmentSearchBinding
import com.example.binapp.domain.model.BinInfo
import com.example.binapp.util.debounce
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = requireNotNull(_binding) { "Binding is null" }

    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchDebounce = debounce<String>(
            SEARCH_DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope,
            false
        ) { query ->
            viewModel.getBinInfo(query)
        }

        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            renderState(state)
        }

        binding.buttonSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            searchDebounce(query)
        }

        binding.etSearch.doOnTextChanged { text, start, before, count ->
            binding.buttonSearch.isEnabled = text.toString().length > 5
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderState(state: SearchScreenState) {

        val isCountryTitleVisible = state.data?.countryName != null
                || state.data?.numeric != null
                || state.data?.alpha2 != null
                || state.data?.emoji != null
                || state.data?.currency != null
                || state.data?.latitude != null
                || state.data?.longitude != null

        val isBankTitleVisible = state.data?.bankName != null
                || state.data?.url != null
                || state.data?.phone != null
                || state.data?.city != null

        with(binding) {
            tvLength.isVisible =
                state is SearchScreenState.Content && state.data?.length != null
            tvLuhn.isVisible = state is SearchScreenState.Content && state.data?.luhn != null
            tvScheme.isVisible =
                state is SearchScreenState.Content && state.data?.scheme != null
            tvType.isVisible = state is SearchScreenState.Content && state.data?.type != null
            tvBrand.isVisible = state is SearchScreenState.Content && state.data?.brand != null
            tvPrepaid.isVisible =
                state is SearchScreenState.Content && state.data?.prepaid != null
            tvCountry.isVisible = isCountryTitleVisible
            tvCountryName.isVisible =
                state is SearchScreenState.Content && state.data?.countryName != null
            tvCountryNumeric.isVisible =
                state is SearchScreenState.Content && state.data?.numeric != null
            tvCountryAlpha2.isVisible =
                state is SearchScreenState.Content && state.data?.alpha2 != null
            tvCountryEmoji.isVisible =
                state is SearchScreenState.Content && state.data?.emoji != null
            tvCountryCurrency.isVisible =
                state is SearchScreenState.Content && state.data?.currency != null
            tvCountryLatitude.isVisible =
                state is SearchScreenState.Content && state.data?.latitude != null
            tvCountryLongitude.isVisible =
                state is SearchScreenState.Content && state.data?.longitude != null
            tvBank.isVisible = isBankTitleVisible
            tvBankName.isVisible =
                state is SearchScreenState.Content && state.data?.bankName != null
            tvBankUrl.isVisible = state is SearchScreenState.Content && state.data?.url != null
            tvBankPhone.isVisible =
                state is SearchScreenState.Content && state.data?.phone != null
            tvBankCity.isVisible =
                state is SearchScreenState.Content && state.data?.city != null
            progressBar.isVisible = state is SearchScreenState.Loading
            tvError.isVisible = state is SearchScreenState.Error
        }
        if (state is SearchScreenState.Error) {
            binding.tvError.text = state.message
        }
        if (state is SearchScreenState.Content && state.data != null) {
            setData(state.data)
        }
    }

    fun setData(data: BinInfo) {
        binding.tvNumber.text =
            String.format(requireContext().getString(R.string.bin_number_string), data.number)
        binding.tvLength.text = String.format(
            requireContext().getString(R.string.length_string),
            data.length.toString()
        )
        binding.tvLuhn.text =
            String.format(requireContext().getString(R.string.luhn_string), data.luhn.toString())
        binding.tvScheme.text =
            String.format(requireContext().getString(R.string.scheme_string), data.scheme)
        binding.tvType.text =
            String.format(requireContext().getString(R.string.type_string), data.type)
        binding.tvBrand.text =
            String.format(requireContext().getString(R.string.brand_string), data.brand)
        binding.tvPrepaid.text = String.format(
            requireContext().getString(R.string.prepaid_string),
            data.prepaid.toString()
        )
        binding.tvCountryName.text = String.format(
            requireContext().getString(R.string.country_name_string),
            data.countryName
        )
        binding.tvCountryNumeric.text =
            String.format(requireContext().getString(R.string.country_numeric_string), data.numeric)
        binding.tvCountryAlpha2.text =
            String.format(requireContext().getString(R.string.country_alpha2_string), data.alpha2)
        binding.tvCountryEmoji.text =
            String.format(requireContext().getString(R.string.country_emoji_string), data.emoji)
        binding.tvCountryCurrency.text = String.format(
            requireContext().getString(R.string.country_currency_string),
            data.currency
        )
        binding.tvCountryLatitude.text = String.format(
            requireContext().getString(R.string.country_latitude_string),
            data.latitude.toString()
        )
        binding.tvCountryLongitude.text = String.format(
            requireContext().getString(R.string.country_longitude_string),
            data.longitude.toString()
        )
        binding.tvBankName.text =
            String.format(requireContext().getString(R.string.bank_name_string), data.bankName)
        binding.tvBankUrl.text =
            String.format(requireContext().getString(R.string.bank_url_string), data.url)
        binding.tvBankPhone.text =
            String.format(requireContext().getString(R.string.bank_phone_string), data.phone)
        binding.tvBankCity.text =
            String.format(requireContext().getString(R.string.bank_city_string), data.city)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 1500L

        fun newInstance() =
            SearchFragment()
    }
}