package com.daddyrusher.jparelationships.onetomany

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/")
class APIController(private val companyRepository: CompanyRepository, private val productRepository: ProductRepository) {
    @GetMapping("/companies")
    fun findAllCompanies(): ResponseEntity<List<Company>> = ResponseEntity.ok(companyRepository.findAll())

    @GetMapping("/products")
    fun findAllProducts(): ResponseEntity<List<Product>> = ResponseEntity.ok(productRepository.findAll())

    @PostMapping("/products")
    fun testSaveProducts(): ResponseEntity<Any> {
        productRepository.deleteAll()
        companyRepository.deleteAll()

        val apple = Company(name = "Apple")
        val samsung = Company(name = "Samsung")

        companyRepository.saveAll(setOf(apple, samsung))

        val iPhone7 = Product(name = "Iphone 7", company = apple)
        val iPadPro = Product(name = "IPadPro", company = apple)

        val galaxyJ7 = Product(name = "GalaxyJ7", company = samsung)
        val galaxyTabA = Product(name = "GalaxyTabA", company = samsung)

        return ResponseEntity.ok(productRepository.saveAll(setOf(iPhone7, iPadPro, galaxyJ7, galaxyTabA)))
    }

    @PostMapping("/companies")
    fun testSaveProductsIntoCompany(): ResponseEntity<Any> {
        productRepository.deleteAll()
        companyRepository.deleteAll()

        val apple = Company(name = "Apple")
        val samsung = Company(name = "Samsung")

        val iphone7 = Product(name = "Iphone 7", company = apple)
        val iPadPro = Product(name = "IPadPro", company = apple)

        val galaxyJ7 = Product(name = "GalaxyJ7", company = samsung)
        val galaxyTabA = Product(name = "GalaxyTabA", company = samsung)

        apple.products = listOf(iphone7, iPadPro).toMutableList()
        samsung.products = listOf(galaxyJ7, galaxyTabA).toMutableList()

        return ResponseEntity.ok(companyRepository.saveAll(listOf(apple, samsung)))
    }
}
