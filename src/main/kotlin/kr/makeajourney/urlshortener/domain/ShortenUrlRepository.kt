package kr.makeajourney.urlshortener.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShortenUrlRepository : JpaRepository<ShortenUrl, Long> {
    fun findByOriginalUrl(url: String): ShortenUrl?
    fun findByShortenUrl(url: String): ShortenUrl?
}
