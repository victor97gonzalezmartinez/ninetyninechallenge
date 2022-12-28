package com.ninetynine.challenge.bridge

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Component
import randomSharePrice

interface PolygonBridge {
    fun currentSharePrices(companyIds: List<String>): List<PolygonSharePrice>
}

@Component
class HttpPolygonBridge(
    private val mapper: Json
): PolygonBridge {
    override fun currentSharePrices(companyIds: List<String>): List<PolygonSharePrice> {
        // "Build headers, build request"
        // "http request"
        val response = mapper.encodeToString(listOf(
            PolygonSharePrice(
                companyId = "AMZN",
                sharePrice = randomSharePrice()
            ),
            PolygonSharePrice(
                companyId = "GOOGL",
                sharePrice = randomSharePrice()
            ),
            PolygonSharePrice(
                companyId = "MSFT",
                sharePrice = randomSharePrice()
            )
        ))
        return mapper.decodeFromString(response)
    }

}

@kotlinx.serialization.Serializable
data class PolygonSharePrice(
    val companyId: String,
    val sharePrice: Double
)

