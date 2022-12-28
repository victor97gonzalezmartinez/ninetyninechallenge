package com.ninetynine.challenge.service

import com.ninetynine.challenge.model.Company
import com.ninetynine.challenge.repository.CompanyRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import randomSharePrice

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {
    fun findAllCompanies(): List<Company> = companyRepository.findAll()
}