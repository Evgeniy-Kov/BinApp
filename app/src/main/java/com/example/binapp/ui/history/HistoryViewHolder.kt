package com.example.binapp.ui.history

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.binapp.R
import com.example.binapp.databinding.ViewBinInfoItemBinding
import com.example.binapp.domain.model.BinInfo

class HistoryViewHolder(
    private val binding: ViewBinInfoItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        binInfo: BinInfo,
        onLongClickListener: OnLongClickListener?,
    ) {
        binding.root.setOnLongClickListener {
            onLongClickListener?.onLongClick(binInfo)
            true
        }

        val isCountryTitleVisible = binInfo.countryName != null
                || binInfo.numeric != null
                || binInfo.alpha2 != null
                || binInfo.emoji != null
                || binInfo.currency != null
                || binInfo.latitude != null
                || binInfo.longitude != null

        val isBankTitleVisible = binInfo.bankName != null
                || binInfo.url != null
                || binInfo.phone != null
                || binInfo.city != null

        binding.tvLength.isVisible = binInfo.length != null
        binding.tvLuhn.isVisible = binInfo.luhn != null
        binding.tvScheme.isVisible = binInfo.scheme != null
        binding.tvType.isVisible = binInfo.type != null
        binding.tvBrand.isVisible = binInfo.brand != null
        binding.tvPrepaid.isVisible = binInfo.prepaid != null
        binding.tvCountry.isVisible = isCountryTitleVisible
        binding.tvCountryName.isVisible = binInfo.countryName != null
        binding.tvCountryNumeric.isVisible = binInfo.numeric != null
        binding.tvCountryAlpha2.isVisible = binInfo.alpha2 != null
        binding.tvCountryEmoji.isVisible = binInfo.emoji != null
        binding.tvCountryCurrency.isVisible = binInfo.currency != null
        binding.tvCountryLatitude.isVisible = binInfo.latitude != null
        binding.tvCountryLongitude.isVisible = binInfo.longitude != null
        binding.tvBank.isVisible = isBankTitleVisible
        binding.tvBankName.isVisible = binInfo.bankName != null
        binding.tvBankUrl.isVisible = binInfo.url != null
        binding.tvBankPhone.isVisible = binInfo.phone != null
        binding.tvBankCity.isVisible = binInfo.city != null
        setData(binInfo)

    }

    fun setData(data: BinInfo) {
        val context = binding.root.context
        binding.tvLength.text =
            String.format(context.getString(R.string.bin_number_string), data.number)
        binding.tvLength.text =
            String.format(context.getString(R.string.length_string), data.length.toString())
        binding.tvLuhn.text =
            String.format(context.getString(R.string.luhn_string), data.luhn.toString())
        binding.tvScheme.text =
            String.format(context.getString(R.string.scheme_string), data.scheme)
        binding.tvType.text = String.format(context.getString(R.string.type_string), data.type)
        binding.tvBrand.text = String.format(context.getString(R.string.brand_string), data.brand)
        binding.tvPrepaid.text =
            String.format(context.getString(R.string.prepaid_string), data.prepaid.toString())
        binding.tvCountryName.text =
            String.format(context.getString(R.string.country_name_string), data.countryName)
        binding.tvCountryNumeric.text =
            String.format(context.getString(R.string.country_numeric_string), data.numeric)
        binding.tvCountryAlpha2.text =
            String.format(context.getString(R.string.country_alpha2_string), data.alpha2)
        binding.tvCountryEmoji.text =
            String.format(context.getString(R.string.country_emoji_string), data.emoji)
        binding.tvCountryCurrency.text =
            String.format(context.getString(R.string.country_currency_string), data.currency)
        binding.tvCountryLatitude.text = String.format(
            context.getString(R.string.country_latitude_string),
            data.latitude.toString()
        )
        binding.tvCountryLongitude.text = String.format(
            context.getString(R.string.country_longitude_string),
            data.longitude.toString()
        )
        binding.tvBankName.text =
            String.format(context.getString(R.string.bank_name_string), data.bankName)
        binding.tvBankUrl.text =
            String.format(context.getString(R.string.bank_url_string), data.url)
        binding.tvBankPhone.text =
            String.format(context.getString(R.string.bank_phone_string), data.phone)
        binding.tvBankCity.text =
            String.format(context.getString(R.string.bank_city_string), data.city)
    }


    fun interface OnLongClickListener {
        fun onLongClick(binInfo: BinInfo)
    }
}