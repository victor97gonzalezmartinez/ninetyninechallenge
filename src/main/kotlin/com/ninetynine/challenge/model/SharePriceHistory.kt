package com.ninetynine.challenge.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class SharePriceHistory(
    val companyId: String,
    val sharePrice: Double,
    val date: LocalDateTime,
    val weekly: Boolean,
    val daily: Boolean,
    val hourly: Boolean
){
    @Id
    lateinit var id: String
}