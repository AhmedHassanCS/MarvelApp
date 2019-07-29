package com.apps.ahfreelancing.marvelapp.data.cloud

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by Ahmed Hassan on 7/27/2019.
 */
object Encryption {
    fun md5(s: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(s.toByteArray())).toString(16).padStart(32, '0')
    }

}