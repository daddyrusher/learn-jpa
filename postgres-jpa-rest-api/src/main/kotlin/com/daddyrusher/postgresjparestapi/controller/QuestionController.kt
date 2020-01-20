package com.daddyrusher.postgresjparestapi.controller

import com.daddyrusher.postgresjparestapi.domain.Question
import com.daddyrusher.postgresjparestapi.exception.ResourceNotFoundException
import com.daddyrusher.postgresjparestapi.repository.QuestionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/questions")
class QuestionController(private val repository: QuestionRepository) {
    @GetMapping
    fun getQuestions(pageable: Pageable): Page<Question> = repository.findAll(pageable)

    @PostMapping
    fun createQuestion(@Valid @RequestBody question: Question): Question = repository.save(question)

    @PutMapping("/{id}")
    fun updateQuestions(@PathVariable id: Long, @Valid @RequestBody questionRequest: Question): Question = repository.findById(id)
            .map {
                it.title = questionRequest.title
                it.description = questionRequest.description
                return@map repository.save(it)
            }.orElseThrow { ResourceNotFoundException("Question not found with id $id") }
}
