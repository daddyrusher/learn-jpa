package com.daddyrusher.jparelationships.onetoone

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserProfileRepository: JpaRepository<UserProfile, Long>
