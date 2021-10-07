package apply.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

fun UserRepository.findByEmail(email: String): User? = findByInformationEmail(email)
fun UserRepository.findAllByEmailIn(emails: List<String>): List<User> = findAllByInformationEmailIn(emails)
fun UserRepository.existsByEmail(email: String): Boolean = existsByInformationEmail(email)

interface UserRepository : JpaRepository<User, Long> {
    @Query("select a from User a where a.information.name like %:keyword% or a.information.email like %:keyword%")
    fun findAllByKeyword(@Param("keyword") keyword: String): List<User>
    fun findByInformationEmail(email: String): User?
    fun findAllByInformationEmailIn(emails: List<String>): List<User>
    fun existsByInformationEmail(email: String): Boolean
}