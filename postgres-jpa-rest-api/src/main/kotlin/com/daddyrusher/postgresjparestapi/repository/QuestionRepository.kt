package com.daddyrusher.postgresjparestapi.repository

import com.daddyrusher.postgresjparestapi.domain.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository: JpaRepository<Question, Long>
