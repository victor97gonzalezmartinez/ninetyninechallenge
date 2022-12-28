package com.ninetynine.challenge.service

import com.ninetynine.challenge.model.SharePriceHistory
import com.ninetynine.challenge.model.TimeRange
import com.ninetynine.challenge.repository.SharePriceHistoryRepository
import org.springframework.stereotype.Service


@Service
class SharePriceHistoryService(
    private val sharePriceHistoryRepository: SharePriceHistoryRepository
) {
    fun findByCompanyIdAndTimeRange(companyId: String, timeRange: TimeRange): List<SharePriceHistory> =
        when(timeRange) {
            TimeRange.WEEKLY -> sharePriceHistoryRepository.findByCompanyIdAndWeeklyIsTrueOrderByDateDesc(companyId)
            TimeRange.DAILY -> sharePriceHistoryRepository.findByCompanyIdAndDailyIsTrueOrderByDateDesc(companyId)
            TimeRange.HOURLY -> sharePriceHistoryRepository.findByCompanyIdAndHourlyIsTrueOrderByDateDesc(companyId)
        }
}