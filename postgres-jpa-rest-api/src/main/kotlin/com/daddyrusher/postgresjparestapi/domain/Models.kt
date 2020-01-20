package com.daddyrusher.postgresjparestapi.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(
        value = ["createdAt", "updatedAt"],
        allowGetters = true
)
abstract class AuditModel : Serializable {
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private var createdAt: LocalDateTime? = LocalDateTime.now()

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private var updatedAt: LocalDateTime? = LocalDateTime.now()
}

@Entity
@Table(name = "questions")
data class Question(

        @Id
        @GeneratedValue(generator = "question_generator")
        @SequenceGenerator(
                name = "question_generator",
                sequenceName = "question_sequence",
                initialValue = 1000
        )
        val id: Long,

        @NotBlank
        @Size(min = 3, max = 100)
        var title: String,

        @Column(columnDefinition = "text")
        var description: String

) : AuditModel()

@Entity
@Table(name = "answers")
data class Answer(
        @Id
        @GeneratedValue(generator = "answer_generator")
        @SequenceGenerator(
                name = "answer_generator",
                sequenceName = "answer_sequence",
                initialValue = 1000
        )
        val id: Long,

        @Column(columnDefinition = "text")
        val text: String,

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "question_id", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnore
        val question: Question
) : AuditModel()
