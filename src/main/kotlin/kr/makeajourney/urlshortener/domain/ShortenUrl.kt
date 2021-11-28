package kr.makeajourney.urlshortener.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@EntityListeners(AuditingEntityListener::class)
@Entity
class ShortenUrl(

    @Column(unique = true)
    val originalUrl: String,

    @Column(unique = true)
    val shortenUrl: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var requestCount: Long = 0

    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var modifiedAt: LocalDateTime

    fun increaseRequestCount() {
        requestCount++
    }
}
