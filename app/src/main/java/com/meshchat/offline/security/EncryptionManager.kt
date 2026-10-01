package com.meshchat.offline.security
import android.content.Context
import android.util.Base64
import java.nio.ByteBuffer
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
class EncryptionManager(private val context:Context){
 private val alias="meshchat-e2ee-v1";private val key:SecretKey by lazy{loadOrCreateKey()}
 private fun loadOrCreateKey():SecretKey{val ks=KeyStore.getInstance("AndroidKeyStore").apply{load(null)};(ks.getKey(alias,null)as?SecretKey)?.let{return it};val gen=KeyGenerator.getInstance("AES","AndroidKeyStore");gen.init(android.security.keystore.KeyGenParameterSpec.Builder(alias,android.security.keystore.KeyProperties.PURPOSE_ENCRYPT or android.security.keystore.KeyProperties.PURPOSE_DECRYPT).setBlockModes(android.security.keystore.KeyProperties.BLOCK_MODE_GCM).setEncryptionPaddings(android.security.keystore.KeyProperties.ENCRYPTION_PADDING_NONE).build());return gen.generateKey()}
 fun encrypt(plain:ByteArray):ByteArray{val c=Cipher.getInstance("AES/GCM/NoPadding");c.init(Cipher.ENCRYPT_MODE,key);val x=c.doFinal(plain);val iv=c.iv;return ByteBuffer.allocate(4+iv.size+x.size).putInt(iv.size).put(iv).put(x).array()}
 fun decrypt(blob:ByteArray):ByteArray{require(blob.size>=4);val b=ByteBuffer.wrap(blob);val n=b.int;require(n in 12..16&&b.remaining()>16);val iv=ByteArray(n);b.get(iv);val x=ByteArray(b.remaining());b.get(x);val c=Cipher.getInstance("AES/GCM/NoPadding");c.init(Cipher.DECRYPT_MODE,key,GCMParameterSpec(128,iv));return c.doFinal(x)}
 fun encryptText(text:String)=Base64.encodeToString(encrypt(text.toByteArray()),Base64.NO_WRAP)
 fun decryptText(value:String)=String(decrypt(Base64.decode(value,Base64.NO_WRAP)))
}
