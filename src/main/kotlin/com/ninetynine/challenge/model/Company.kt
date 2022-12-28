package com.ninetynine.challenge.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Company(
    @Id
    val id: String,
    val name: String,
    val sharePrice: Double
)