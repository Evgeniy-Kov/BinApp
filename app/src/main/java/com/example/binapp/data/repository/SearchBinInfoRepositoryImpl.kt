package com.example.binapp.data.repository

import com.example.binapp.data.NetworkClient
import com.example.binapp.data.network.BinRequest
import com.example.binapp.data.network.BinResponse
import com.example.binapp.domain.api.SearchBinInfoRepository
import com.example.binapp.domain.model.BinInfo
import com.example.binapp.domain.model.Resource

class SearchBinInfoRepositoryImpl(
    private val networkClient: NetworkClient
) : SearchBinInfoRepository {
    override suspend fun searchBinInfo(number: String): Resource<BinInfo> {
        val response = networkClient.doRequest(BinRequest(number))

        return if (response is BinResponse) {

            val dto = response.result
            Resource.Success(
                BinInfo(
                    number = number,
                    length = dto.number?.length,
                    luhn = dto.number?.luhn,
                    scheme = dto.scheme,
                    type = dto.type,
                    brand = dto.brand,
                    prepaid = dto.prepaid,
                    countryName = dto.country?.name,
                    numeric = dto.country?.numeric,
                    alpha2 = dto.country?.alpha2,
                    emoji = dto.country?.emoji,
                    currency = dto.country?.currency,
                    latitude = dto.country?.latitude,
                    longitude = dto.country?.longitude,
                    bankName = dto.bank?.name,
                    url = dto.bank?.url,
                    phone = dto.bank?.phone,
                    city = dto.bank?.city
                )
            )
        } else {
            Resource.Error(response.resultCode)
        }

    }
}