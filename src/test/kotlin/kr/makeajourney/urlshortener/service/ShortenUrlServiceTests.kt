package kr.makeajourney.urlshortener.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ShortenUrlServiceTests(
    @Autowired
    private val shortenUrlService: ShortenUrlService,
) {

    @Test
    fun testGenerateShortenUrlFromOriginalUrl() {
        val originalUrl = "https://en.wikipedia.org/wiki/URL_shortening"
        val shortenUrl = shortenUrlService.getShortenUrl(originalUrl)
        assertThat(shortenUrl).isNotEmpty
    }

    @Test
    fun testSameShortenUrlFromOriginalUrl() {
        val originalUrl = "https://en.wikipedia.org/wiki/URL_shortening"
        val shortenUrl1 = shortenUrlService.getShortenUrl(originalUrl)
        val shortenUrl2 = shortenUrlService.getShortenUrl(originalUrl)
        assertThat(shortenUrl1).isEqualTo(shortenUrl2)
    }
}