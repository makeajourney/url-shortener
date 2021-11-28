# url-shortener

## pre-requirements
Java 11


## run 

```shell
git clone https://github.com/makeajourney/url-shortener.git
cd url-shortener/
./gradlew build
java -jar build/libs/urlshortener-0.0.1-SNAPSHOT.jar
```

# test

## 단축 URL 요청 예

요청 예 :

GET http://localhost:8080/v1/url/shorten?url=원본URL

```
http://localhost:8080/v1/url/shorten?url=https://en.wikipedia.org/wiki/URL_shortening
```

## 단축 URL을 이용한 원본 URL로 Redirect

위 단축 URL 요청 API 를 사용하여 응답받은 값으로 요청하면 원본 URL로 Redirect 됨.
