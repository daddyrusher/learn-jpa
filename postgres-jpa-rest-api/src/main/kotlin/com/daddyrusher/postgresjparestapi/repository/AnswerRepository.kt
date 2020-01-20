package com.daddyrusher.postgresjparestapi.repository

import com.daddyrusher.postgresjparestapi.domain.Answer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository: JpaRepository<Answer, Long>
