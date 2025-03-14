package com.example.binapp.data.db.converter

import com.example.binapp.data.db.entity.BinInfoEntity
import com.example.binapp.domain.model.BinInfo

class BinInfoDbConverter {
    fun map(binInfo: BinInfo): BinInfoEntity {
        return BinInfoEntity(
            number = binInfo.number,
            length = binInfo.length,
            luhn = binInfo.luhn,
            scheme = binInfo.scheme,
            type = binInfo.type,
            brand = binInfo.brand,
            prepaid = binInfo.prepaid,
            countryName = binInfo.countryName,
            numeric = binInfo.numeric,
            alpha2 = binInfo.alpha2,
            emoji = binInfo.emoji,
            currency = binInfo.currency,
            latitude = binInfo.latitude,
            longitude = binInfo.longitude,
            bankName = binInfo.bankName,
            url = binInfo.url,
            phone = binInfo.phone,
            city = binInfo.city
        )
    }

    fun map(binInfo: BinInfoEntity): BinInfo {
        return BinInfo(
            number = binInfo.number,
            length = binInfo.length,
            luhn = binInfo.luhn,
            scheme = binInfo.scheme,
            type = binInfo.type,
            brand = binInfo.brand,
            prepaid = binInfo.prepaid,
            countryName = binInfo.countryName,
            numeric = binInfo.numeric,
            alpha2 = binInfo.alpha2,
            emoji = binInfo.emoji,
            currency = binInfo.currency,
            latitude = binInfo.latitude,
            longitude = binInfo.longitude,
            bankName = binInfo.bankName,
            url = binInfo.url,
            phone = binInfo.phone,
            city = binInfo.city
        )
    }
}