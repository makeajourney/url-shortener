package kr.makeajourney.urlshortener.web

import kr.makeajourney.urlshortener.service.ShortenUrlService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI


@RestController
class ShortenUrlController(
    @Value("\${host-url}")
    private val hostUrl: String,

    private val shortenUrlService: ShortenUrlService,
) {

    @GetMapping("/v1/url/shorten")
    fun getShortenUrl(@RequestParam url: String): String {
        if (url.endsWith("/")) {
            shortenUrlService.getShortenUrl(url.trim('/'))
        }
        return "${hostUrl}/${shortenUrlService.getShortenUrl(url)}"
    }

    @GetMapping("/{shortenUrl}")
    fun redirectOriginalUrl(@PathVariable shortenUrl: String): ResponseEntity<Any> {
        val originalUrl = shortenUrlService.getOriginalUrl(shortenUrl)
        val redirectUri = URI(originalUrl)
        val httpHeaders = HttpHeaders()
        httpHeaders.location = redirectUri
        return ResponseEntity<Any>(httpHeaders, HttpStatus.SEE_OTHER)
    }
}
