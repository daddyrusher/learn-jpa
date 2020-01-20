package com.daddyrusher.jparelationships

import com.daddyrusher.jparelationships.manytomany.Post
import com.daddyrusher.jparelationships.manytomany.PostRepository
import com.daddyrusher.jparelationships.manytomany.Tag
import com.daddyrusher.jparelationships.manytomany.TagRepository
import com.daddyrusher.jparelationships.onetoone.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@SpringBootApplication
class JpaRelationshipsApplication: CommandLineRunner {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var userProfileRepository: UserProfileRepository

    override fun run(vararg args: String?) {
        userRepository.deleteAllInBatch()
        userProfileRepository.deleteAllInBatch()

        val user = User(firstName = "Maksim",
                        lastName = "Zanin",
                        email = "daddyrusher@gmail.com",
                        password = "GOD_PWD")
        val dateOfBirth = Calendar.getInstance()
        dateOfBirth.set(1994, 8, 4)

        val userProfile = UserProfile(
                phoneNumber = "49494949494",
                gender = Gender.MALE,
                dateOfBirth = LocalDateTime.ofInstant(dateOfBirth.toInstant(), ZoneId.systemDefault()),
                address1 = "434",
                address2 = "222",
                street = "Pushkina",
                city = "Perm",
                state = "Perm",
                country = "Russia",
                zipCode = "614700"
        )

        user.userProfile = userProfile
        userProfile.user = user
        userRepository.save(user)
    }

    // many to many test
    /*@Autowired
    lateinit var tagRepository: TagRepository
    @Autowired
    lateinit var postRepository: PostRepository

    override fun run(vararg args: String) {
        postRepository.deleteAllInBatch()
        tagRepository.deleteAllInBatch()

        val post = Post(title =
                "Learn how to map a many to many relationship using hibernate",
                description = "Entire Post content with Sample code")

        val tag1 = Tag(name = "Spring Boot")
        val tag2 = Tag(name = "Hibernate")

        post.tags.add(tag1)
        post.tags.add(tag2)

        tag1.posts.add(post)
        tag2.posts.add(post)

        postRepository.save(post)
    }*/
}

fun main(args: Array<String>) {
    runApplication<JpaRelationshipsApplication>(*args)
}
