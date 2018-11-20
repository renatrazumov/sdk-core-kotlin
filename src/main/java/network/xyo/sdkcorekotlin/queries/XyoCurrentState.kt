package network.xyo.sdkcorekotlin.queries

import network.xyo.sdkcorekotlin.signing.XyoSigner

interface XyoCurrentState {
    /**
     * Gets the all of signers to use when creating the next origin block.
     *
     * @return all of the signers.
     */
    fun getSigners () : Array<XyoSigner>

    /**
     * The previous hash to be included in the next origin block.
     */
    val previousHash : ByteArray?

    /**
     * The index of the origin chain.
     */
    val index : ByteArray

    /**
     * The next public key to be used in the origin chain.
     */
    var nextPublicKey : ByteArray?
}