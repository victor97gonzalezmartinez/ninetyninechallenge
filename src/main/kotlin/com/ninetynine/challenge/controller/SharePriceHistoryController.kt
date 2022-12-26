package com.ninetynine.challenge.controller

import com.ninetynine.challenge.model.SharePriceHistory
import com.ninetynine.challenge.model.TimeRange
import com.ninetynine.challenge.service.SharePriceHistoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/share-price")
class SharePriceHistoryController(
    private val sharePriceHistoryService: SharePriceHistoryService
) {
    @GetMapping
    fun findByCompanyIdAndTimeRange(@RequestParam companyId: String, @RequestParam timeRange: TimeRange): List<SharePriceHistory> =
        sharePriceHistoryService.findByCompanyIdAndTimeRange(companyId, timeRange)
}