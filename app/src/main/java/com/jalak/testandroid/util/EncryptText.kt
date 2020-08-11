package com.jalak.testandroid.util

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec


class EncryptText {
  fun encryptRut(cleartext: String): String {
    val PASSWORD_ENC_SECRET = "ionix123456"
    try {
      val keySpec = DESKeySpec(
        PASSWORD_ENC_SECRET.toByteArray(charset("UTF-8"))
      )
      val keyFactory =
        SecretKeyFactory.getInstance("DES")
      val key = keyFactory.generateSecret(keySpec)
      val cipher = Cipher.getInstance("DES")
      cipher.init(Cipher.ENCRYPT_MODE, key)
      return Base64.encodeToString(
        cipher.doFinal(
          cleartext
            .toByteArray(charset("UTF-8"))
        ), Base64.DEFAULT
      )
    } catch (e: Exception) {
    }
    return cleartext.trim()
  }
}
