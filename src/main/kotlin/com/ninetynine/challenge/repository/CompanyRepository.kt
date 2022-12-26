package com.ninetynine.challenge.repository

import com.ninetynine.challenge.model.Company
import org.springframework.data.mongodb.repository.MongoRepository

interface CompanyRepository: MongoRepository<Company, String> {
}