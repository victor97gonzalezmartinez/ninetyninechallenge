package com.ninetynine.challenge.repository

import com.ninetynine.challenge.model.SharePriceHistory
import org.springframework.data.mongodb.repository.MongoRepository

interface SharePriceHistoryRepository: MongoRepository<SharePriceHistory, String> {
    fun findByCompanyIdAndWeeklyIsTrue(companyId: String): List<SharePriceHistory>
    fun findByCompanyIdAndDailyIsTrue(companyId: String): List<SharePriceHistory>
    fun findByCompanyIdAndHourlyIsTrue(companyId: String): List<SharePriceHistory>
}