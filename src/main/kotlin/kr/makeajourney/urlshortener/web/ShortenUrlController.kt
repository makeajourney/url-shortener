package kr.makeajourney.urlshortener.web

import kr.makeajourney.urlshortener.service.ShortenUrlService
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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
}
