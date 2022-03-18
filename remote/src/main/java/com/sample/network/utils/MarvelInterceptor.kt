package com.sample.network.utils

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MarvelInterceptor(private val privateKey: String, private val publicKey: String) :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val timeStamp = System.currentTimeMillis()
        val hash = generateHashForMarvelServer(privateKey, publicKey, timeStamp)
        val original = chain.request()
        val originalHttpUrl = original.url()
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(TIME_STAMP, timeStamp.toString())
            .addQueryParameter(API_KEY, publicKey)
            .addQueryParameter(HASH, hash)
            .build()
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    /**
     * this method returns
     *
     * @param timeStamp current time in milli seconds
     * @return special hash key to access marvel services.
     */
    private fun generateHashForMarvelServer(
        privateKey: String,
        publicKey: String,
        timeStamp: Long
    ): String {
        val hash = timeStamp.toString() + privateKey + publicKey
        return md5(hash)
    }

    private fun md5(hash: String): String {
        val MD5 = "MD5"
        try {
            val digest = MessageDigest.getInstance(MD5)
            digest.update(hash.toByteArray())
            val messageDigest = digest.digest()
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                val stringBuilder =
                    StringBuilder(Integer.toHexString(0xFF and aMessageDigest.toInt()))
                while (stringBuilder.length < 2) {
                    stringBuilder.insert(0, "0")
                }
                hexString.append(stringBuilder)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            print(e.localizedMessage)
        }
        return ""
    }

    companion object {
        private const val API_KEY = "apikey"
        private const val TIME_STAMP = "ts"
        private const val HASH = "hash"
    }

}