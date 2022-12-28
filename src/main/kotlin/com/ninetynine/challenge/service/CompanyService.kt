package com.ninetynine.challenge.service

import com.ninetynine.challenge.model.Company
import com.ninetynine.challenge.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {
    fun findAllCompanies(): List<Company> = companyRepository.findAll()
}