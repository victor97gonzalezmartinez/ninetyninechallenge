package com.ninetynine.challenge.repository

import com.ninetynine.challenge.model.SharePriceHistory
import org.springframework.data.mongodb.repository.MongoRepository

interface SharePriceHistoryRepository: MongoRepository<SharePriceHistory, String> {
    fun findByCompanyIdAndWeeklyIsTrueOrderByDateDesc(companyId: String): List<SharePriceHistory>
    fun findByCompanyIdAndDailyIsTrueOrderByDateDesc(companyId: String): List<SharePriceHistory>
    fun findByCompanyIdAndHourlyIsTrueOrderByDateDesc(companyId: String): List<SharePriceHistory>
}