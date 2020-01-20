package com.daddyrusher.jparelationships.manytomany

import org.hibernate.annotations.NaturalId
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "posts")
data class Post (
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @NotNull
        @Size(max = 100)
        @Column(unique = true)
        val title: String = "",

        @NotNull
        @Size(max = 250)
        val description: String = "",

        @NotNull
        @Lob
        val content: String = "",

        @NotNull
        @Column(name = "posted_at")
        val postedAt: LocalDateTime = LocalDateTime.now(),

        @NotNull
        @Column(name = "last_updated_at")
        val lastUpdatedAt: LocalDateTime = LocalDateTime.now(),

        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinTable(name = "post_tags",
                joinColumns = [JoinColumn(name = "post_id")],
                inverseJoinColumns = [JoinColumn(name = "tag_id")])
        var tags: MutableSet<Tag> = mutableSetOf()
) {
        override fun equals(other: Any?): Boolean {
                return super.equals(other)
        }

        override fun hashCode(): Int {
                return super.hashCode()
        }

        override fun toString(): String {
                return super.toString()
        }
}

@Entity
@Table(name = "tags")
data class Tag (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @NotNull
        @Size(max = 100)
        @NaturalId
        val name: String = "",

        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "tags")
        var posts: MutableSet<Post> = mutableSetOf()
) {
        override fun equals(other: Any?): Boolean {
                return super.equals(other)
        }

        override fun hashCode(): Int {
                return super.hashCode()
        }

        override fun toString(): String {
                return super.toString()
        }
}
