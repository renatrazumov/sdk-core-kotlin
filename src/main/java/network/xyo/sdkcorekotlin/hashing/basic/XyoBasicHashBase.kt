package network.xyo.sdkcorekotlin.hashing.basic

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import network.xyo.sdkcorekotlin.data.XyoByteArrayReader
import network.xyo.sdkcorekotlin.data.XyoObject
import network.xyo.sdkcorekotlin.exceptions.XyoCorruptDataException
import network.xyo.sdkcorekotlin.hashing.XyoHash
import java.security.MessageDigest

/**
 * A base class for fixed size hashes.
 *
 * @param hash, the created hash.
 */
abstract class XyoBasicHashBase (override val hash : ByteArray): XyoHash() {
    override val sizeIdentifierSize: Int? = null

    /**
     * A base class for creating Standard Java hashes supported by MessageDigest.
     */
    abstract class XyoBasicHashBaseProvider : XyoHashProvider() {
        /**
         * The MessageDigest instance key. e.g. "SHA-256"
         */
        abstract val standardDigestKey : String
        override val sizeOfBytesToGetSize: Int? = 0

        override fun createHash (data: ByteArray) : Deferred<XyoHash> {
            return GlobalScope.async {
                return@async object : XyoBasicHashBase(hash(data)) {
                    override val id: ByteArray
                        get() = byteArrayOf(major, minor)
                }
            }
        }

        private fun hash(data: ByteArray): ByteArray {
            return MessageDigest.getInstance(standardDigestKey).digest(data)
        }

        override fun createFromPacked(byteArray: ByteArray): XyoObject {
            if (readSize(byteArray) != byteArray.size) {
                throw XyoCorruptDataException("Invalid size!")
            }

            val hash = XyoByteArrayReader(byteArray).read(0, byteArray.size)
            return object : XyoBasicHashBase(hash) {
                override val id: ByteArray
                    get() = byteArrayOf(major, minor)
            }
        }
    }
}