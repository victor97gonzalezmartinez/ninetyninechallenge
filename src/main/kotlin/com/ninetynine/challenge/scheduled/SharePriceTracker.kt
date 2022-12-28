package com.ninetynine.challenge.scheduled

import com.ninetynine.challenge.model.SharePriceHistory
import com.ninetynine.challenge.repository.CompanyRepository
import com.ninetynine.challenge.repository.SharePriceHistoryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import randomSharePrice
import java.time.LocalDateTime

@Component
// SharePriceUpdater, SharePriceRefresher
class SharePriceTracker(
    private val companyRepository: CompanyRepository,
    private val sharePriceHistoryRepository: SharePriceHistoryRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Scheduled(cron = "0/20 * * 1/1 * ?")
    fun refreshSharePrices() {
        logger.info("About to refresh sharePrices")
        val date = LocalDateTime.now()
        companyRepository.findAll().map { company ->
            companyRepository.save(company.copy(sharePrice = randomSharePrice())).also {
                val sharePriceHistory = SharePriceHistory(
                    companyId = it.id,
                    sharePrice = it.sharePrice,
                    date = date,
                    weekly = checkWeekly(date),
                    daily = checkDaily(date),
                    hourly = checkHourly(date),
                )
                sharePriceHistoryRepository.insert(sharePriceHistory)
            }
        }
    }

    private fun checkHourly(date: LocalDateTime) =
        (date.minute % 5  == 0) && date.second < 20

    private fun checkDaily(date: LocalDateTime) =
        (date.minute % 30  == 0) && date.second < 20

    private fun checkWeekly(date: LocalDateTime) =
        (date.hour % 6 == 0) && date.minute == 0 && date.second < 20

}