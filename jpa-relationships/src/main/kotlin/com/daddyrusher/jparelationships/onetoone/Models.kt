package com.daddyrusher.jparelationships.onetoone

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
data class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @NotNull
        @Size(max = 65)
        @Column(name = "first_name")
        val firstName: String = "",

        @Size(max = 65)
        @Column(name = "last_name")
        val lastName: String = "",

        @NotNull
        @Email
        @Size(max = 100)
        @Column(unique = true)
        val email: String = "",

        @NotNull
        @Size(max = 128)
        val password: String = "",

        @OneToOne(fetch = FetchType.LAZY,
                cascade =  [CascadeType.ALL],
                mappedBy = "user")
        var userProfile: UserProfile? = null
)

enum class Gender { DEFAULT, MALE, FEMALE }

@Entity
@Table(name = "user_profiles")
data class UserProfile(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(name = "phone_number")
        @Size(max = 15)
        val phoneNumber: String = "",

        @Enumerated(EnumType.STRING)
        @Column(length = 10)
        val gender: Gender = Gender.DEFAULT,

        @Column(name = "dob")
        val dateOfBirth: LocalDateTime = LocalDateTime.now(),

        @Size(max = 100)
        val address1: String = "",

        @Size(max = 100)
        val address2: String = "",

        @Size(max = 100)
        val street: String = "",

        @Size(max = 100)
        val city: String = "",

        @Size(max = 100)
        val state: String = "",

        @Size(max = 100)
        val country: String = "",

        @Column(name = "zip_code")
        @Size(max = 32)
        val zipCode: String = "",

        @OneToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "user_id", nullable = false)
        var user: User? = null
)
