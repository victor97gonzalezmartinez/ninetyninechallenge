package com.ninetynine.challenge.controller

import com.ninetynine.challenge.model.Company
import com.ninetynine.challenge.service.CompanyService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/companies")
class CompanyController(
    private val companyService: CompanyService
) {
    @GetMapping
    fun findAllCompanies(): List<Company> = companyService.findAllCompanies()
}