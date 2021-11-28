package kr.makeajourney.urlshortener.service

import kr.makeajourney.urlshortener.domain.ShortenUrl
import kr.makeajourney.urlshortener.domain.ShortenUrlRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import kotlin.random.Random

@Service
class ShortenUrlService(
    private val shortenUrlRepository: ShortenUrlRepository,
) {

    private val characterPool = ('a'..'z') + ('A'..'Z')

    @Transactional
    fun getShortenUrl(originalUrl: String): String {
        return shortenUrlRepository.findByOriginalUrl(originalUrl)?.shortenUrl
            ?: saveShortenUrl(originalUrl)
    }

    private fun saveShortenUrl(originalUrl: String): String {
        val generatedString = generateUniqueStringFor3times() ?: throw IllegalStateException("다시 시도해주세요.")
        val entity = ShortenUrl(originalUrl, generatedString)
        shortenUrlRepository.save(entity)
        return entity.shortenUrl
    }

    private fun generateUniqueStringFor3times(): String? {
        (1..3).forEach { _ ->
            val generatedString = generateRandomString()
            shortenUrlRepository.findByShortenUrl(generatedString)
                ?: return generatedString
        }
        return null
    }

    private fun generateRandomString(): String {
        return (1..SHORTEN_URL_LENGTH)
            .map { Random.nextInt(0, characterPool.size) }
            .map(characterPool::get)
            .joinToString("")
    }

    companion object {
        private const val SHORTEN_URL_LENGTH = 8
    }
}
