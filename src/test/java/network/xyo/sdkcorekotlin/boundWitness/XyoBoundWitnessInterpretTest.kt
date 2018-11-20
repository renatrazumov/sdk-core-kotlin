package network.xyo.sdkcorekotlin.boundWitness

import kotlinx.coroutines.runBlocking
import network.xyo.sdkcorekotlin.XyoTestBase
import network.xyo.sdkcorekotlin.hashing.basic.XyoSha256
import network.xyo.sdkobjectmodelkotlin.objects.sets.XyoObjectIterator
import org.junit.Assert
import org.junit.Test
import java.math.BigInteger

class XyoBoundWitnessInterpretTest : XyoTestBase() {

    @Test
    fun testInterpreterBoundWitness () {
        val boundWitnessBytes = BigInteger("A0020000016AB0010000009AA0040000004A801200000044254E2A6880A5D89A45F3A7DF8936E907528D83AF8E004BC140A259B3AF02115A948F5D47E784AFDA8FFA2250F9AF971A55FBAFF04638C076BE1A25107B8D11300000004A801200000044D615930DBCCCCB4F0DA701559A50771824A4EC974E431A1E415851D8130D23417C9F07D96D351DE694C9B6C574EFFF1AB177DCF5BA0350CB27CCFB2C8F834509B00100000026A01800000010A00100000004A0010000000400000010A00100000004A00100000004B001000000A0A0030000004E8014000000482100C5D765DE29403620AA3C336925F48CA759494740E20EFFD96880F449B0F2C10C2100E0D17E637D20F4F3E4CB61485346F533004704ADA671FB77F31D806B8F376E910000004C8014000000462054E948BA815E1F8888A85D0E6FEA73CD56ADEAA88B0DFEF767134973F7C3B824203703414943F30EB61E865F07825DBA861362AFC06BBBFAB25C873F4444752116", 16).toByteArray()
        val createdBoundWitness = XyoBoundWitness.getInstance(boundWitnessBytes.copyOfRange(1, boundWitnessBytes.size))

        Assert.assertEquals(2, XyoObjectIterator(createdBoundWitness.publicKeys).size)
        Assert.assertEquals(2, XyoObjectIterator(createdBoundWitness.payloads).size)
        Assert.assertEquals(2, XyoObjectIterator(createdBoundWitness.signatures).size)
    }

    @Test
    fun testBoundWitnessHash () {
        runBlocking {
            val boundWitnessHash = BigInteger("8009000000244678BE6DFC7593719DDCF77380A885BFD5EE72017BC8A767EA384D4AC8C40F47", 16).toByteArray()
            val boundWitnessBytes = BigInteger("A0020000016AB0010000009AA0040000004A801200000044254E2A6880A5D89A45F3A7DF8936E907528D83AF8E004BC140A259B3AF02115A948F5D47E784AFDA8FFA2250F9AF971A55FBAFF04638C076BE1A25107B8D11300000004A801200000044D615930DBCCCCB4F0DA701559A50771824A4EC974E431A1E415851D8130D23417C9F07D96D351DE694C9B6C574EFFF1AB177DCF5BA0350CB27CCFB2C8F834509B00100000026A01800000010A00100000004A0010000000400000010A00100000004A00100000004B001000000A0A0030000004E8014000000482100C5D765DE29403620AA3C336925F48CA759494740E20EFFD96880F449B0F2C10C2100E0D17E637D20F4F3E4CB61485346F533004704ADA671FB77F31D806B8F376E910000004C8014000000462054E948BA815E1F8888A85D0E6FEA73CD56ADEAA88B0DFEF767134973F7C3B824203703414943F30EB61E865F07825DBA861362AFC06BBBFAB25C873F4444752116", 16).toByteArray()
            val createdBoundWitness = XyoBoundWitness.getInstance(boundWitnessBytes.copyOfRange(1, boundWitnessBytes.size))

            val hash = createdBoundWitness.getHash(XyoSha256).await().self
            Assert.assertArrayEquals(boundWitnessHash.copyOfRange(1, boundWitnessHash.size), hash)
        }
    }
}