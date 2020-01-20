package com.daddyrusher.jparelationships.onetomany

import javax.persistence.*

@Entity
@Table(name = "company")
data class Company(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,
        var name: String = "",
        @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        var products: MutableList<Product> = mutableListOf()
)

@Entity
@Table
data class Product(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,
        var name: String = "",
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "company_id")
        val company: Company? = null
)

