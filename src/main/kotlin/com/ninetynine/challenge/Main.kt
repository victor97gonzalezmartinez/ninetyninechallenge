package com.ninetynine.challenge

import com.ninetynine.challenge.model.Company
import com.ninetynine.challenge.model.SharePriceHistory
import com.ninetynine.challenge.repository.CompanyRepository
import com.ninetynine.challenge.repository.SharePriceHistoryRepository
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.bind.annotation.RestController
import randomSharePrice
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@EnableScheduling
@SpringBootApplication
class ChallengeNinetynineApplication

fun main(args: Array<String>) {
	runApplication<ChallengeNinetynineApplication>(*args)
}

@Configuration
@EnableSwagger2
class SwaggerConfig {
	@Bean
	fun api(): Docket {
		return Docket(DocumentationType.SWAGGER_2).select().apis(
			RequestHandlerSelectors.withClassAnnotation(RestController::class.java)
		).paths(PathSelectors.any())
			.build()
	}
}

@Configuration
class KotlinSerializationConfiguration {
	@Bean
	fun json() = Json
}

@Configuration
class InitDataConfiguration {
	private val logger = LoggerFactory.getLogger(javaClass)

	@Autowired
	private lateinit var companyRepository: CompanyRepository

	@Autowired
	private lateinit var sharePriceHistoryRepository: SharePriceHistoryRepository

	@Bean
	fun initData() {
		logger.info("About to create some data")
		clearPreviousData()

		val companyIds = initCompanyData()
		initSharePriceHistoryData(companyIds)
	}

	private fun clearPreviousData() {
		companyRepository.deleteAll()
		sharePriceHistoryRepository.deleteAll()
	}

	private fun initCompanyData(): List<String> {
		logger.info("About to init Company data")
		val amazon = Company(
			id = "AMZN",
			name = "Amazon",
			sharePrice = 80.22
		)
		val google = Company(
			id = "GOOGL",
			name = "Alphabet",
			sharePrice = 89.17
		)
		val microsoft = Company(
			id = "MSFT",
			name = "Microsoft",
			sharePrice = 99.58
		)
		val companies = listOf(amazon, google, microsoft)
		return companyRepository.insert(companies).map { it.id }
	}

	private fun initSharePriceHistoryData(companyIds: List<String>) {
		logger.info("About to init SharePriceHistory data")
		val now = LocalDateTime.now()

		companyIds.map { companyId ->
			var lastFiveMinutesFromNow = now.truncatedTo(ChronoUnit.HOURS)
				.plusMinutes((5 * (now.minute / 5)).toLong())

			// One day has 1440 minutes, so it has 288 times 5 minutes. This represents data from one day ago
			repeat(288) {
				lastFiveMinutesFromNow = lastFiveMinutesFromNow.minusMinutes(5)
				val daily: Boolean = (it % 6 == 0) // every 30 minutes
				val weekly: Boolean = (it % 72 == 0) // every 6 hours
				val sharePrice = SharePriceHistory(
					companyId = companyId,
					sharePrice = randomSharePrice(),
					date = lastFiveMinutesFromNow,
					hourly = true,
					daily = daily,
					weekly = weekly
				)
				sharePriceHistoryRepository.insert(sharePrice)
			}
		}
	}
}
