package com.ninetynine.challenge.repository

import com.ninetynine.challenge.model.SharePriceHistory
import org.springframework.data.mongodb.repository.MongoRepository

interface SharePriceHistoryRepository: MongoRepository<SharePriceHistory, String> {
    // maybe custom queries in prod

    // use projections to avoid showing flag fields
    // limit results to X amount or remove flag from old registers.
    // Maybe have different collections for each time series?
    fun findByCompanyIdAndWeeklyIsTrueOrderByDateDesc(companyId: String): List<SharePriceHistory>
    fun findByCompanyIdAndDailyIsTrueOrderByDateDesc(companyId: String): List<SharePriceHistory>
    fun findByCompanyIdAndHourlyIsTrueOrderByDateDesc(companyId: String): List<SharePriceHistory>
}